package cn.settile.lzjyzq2.compressor.util;

import cn.settile.lzjyzq2.compressor.model.*;
import com.github.hypfvieh.util.CompressionUtil;

import javax.annotation.Nonnull;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class CompressUtil {

    private final Map<String, Filter> filterCache = new HashMap<>();
    private final Map<Filter, String> pathFilters = new HashMap<>();

    private CompressorConfig compressorConfig = null;

    public CompressUtil() {

    }

    public void use(CompressorConfig compressorConfig) {
        this.compressorConfig = compressorConfig;
        filterCache.clear();
        pathFilters.clear();
        convertFileNodes(compressorConfig.getSources().getFileNodes());
        convertFolderNodes(compressorConfig.getSources().getFolderNodes());
        convertFolderNodes(compressorConfig.getSources().getFolderNodes());
        convertToPathFilters();
    }

    private void convertFileNodes(List<FileNode> fileNodes) {
        if (fileNodes != null) {
            fileNodes.forEach(fileNode -> {
                Filter filter = new Filter();
                filter.setParent(fileNode.getParent());
                filter.setSources(filter.getSources());
                filter.setRegex(fileNode.getName());
                filter.setId(fileNode.getId());
                filter.setIncludesFile(true);
                filter.setIncludesFolder(false);
                filter.setFilterType(FilterType.FILE);
                filterCache.put(fileNode.getId(), filter);
            });
        }
    }

    private void convertFolderNodes(List<FolderNode> folderNodes) {
        if (folderNodes != null) {
            folderNodes.forEach(folderNode -> {
                Filter filter = new Filter();
                filter.setParent(folderNode.getParent());
                filter.setSources(filter.getSources());
                filter.setRegex(folderNode.getName());
                filter.setId(folderNode.getId());
                filter.setIncludesFile(false);
                filter.setIncludesFolder(true);
                convertFileNodes(folderNode.getFileNodes());
                convertFilters(folderNode.getFilters());
                convertFolderNodes(folderNode.getFolderNodes());
                filter.setFilterType(FilterType.FOLDER);
                filterCache.put(folderNode.getId(), filter);
            });
        }
    }

    private void convertFilters(List<Filter> filters) {
        if (filters != null) {
            filters.forEach(filter -> filterCache.put(filter.getId(), filter));
        }
    }

    private void copyToTmpDir() {
        if (compressorConfig == null) {
            throw new RuntimeException("为找到配置");
        }
        String tmpDir = compressorConfig.getTmpDir();
        if (tmpDir == null || tmpDir.isBlank()) {
            tmpDir = System.getProperty("java.io.tmpdir") + Var.TEMP_FOLDER_NAME + File.separator;
        }
        File tmpDirFile = Paths.get(tmpDir).toFile();
        if (!tmpDirFile.isDirectory()) {
            throw new RuntimeException("[" + tmpDir + "]不是文件夹");
        }
        if (!tmpDirFile.exists()) {
            if (!tmpDirFile.mkdir()) {
                throw new RuntimeException("临时文件夹[" + tmpDir + "]创建失败");
            }
        }
        if (!tmpDirFile.canWrite()) {
            throw new RuntimeException("临时文件夹[" + tmpDir + "]无法写入");
        }
        // 开始拷贝

    }

    private void copyOut(Out out) {
        if (out.getId() != null && !out.getId().isBlank()) {
            Filter outFilter = filterCache.get(out.getId());

        }
    }


    public List<Filter> getFiltersWithOut(Out out) {
        List<Filter> filters = new LinkedList<>();
        Filter filter = filterCache.get(out.getId());
        String dirPath = getFilterDir(filter);
        pathFilters.forEach(((filterKey, s) -> {
            if (dirPath.contains(s) && filterKey.getFilterType().equals(FilterType.FILTER)) {
                filters.add(filterKey);
            }
        }));
        return filters;
    }

    private void convertToPathFilters() {
        pathFilters.clear();
        filterCache.values().forEach(filter -> pathFilters.put(filter, getFilterDir(filter)));
    }

    public String getFilterDir(Filter filter) {
        return getFolderPath(filter.getParent());
    }

    public String getFileDir(FileNode fileNode) {
        return getFolderPath(fileNode.getParent());
    }

    public String getFolderDir(FolderNode folderNode) {
        return getFolderPath(folderNode.getParent());
    }

    public String getFolderPath(@Nonnull FolderNode folderNode) {
        List<String> pathList = new LinkedList<>();
        FolderNode tmp = folderNode;
        while (tmp != null) {
            pathList.add(tmp.getName());
            tmp = tmp.getParent();
        }
        pathList.add(folderNode.getSources().getSrc());
        Collections.reverse(pathList);
        return pathList.stream().collect(Collectors.joining(File.separator, "", File.separator));
    }

    public void release() {
        filterCache.clear();
        pathFilters.clear();
        compressorConfig = null;
    }
}

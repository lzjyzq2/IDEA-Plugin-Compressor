package cn.settile.lzjyzq2.compressor.util;

import cn.settile.lzjyzq2.compressor.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfigConverterTest {


    @Test
    void convert() {
        String xml = "<compressor-config>\n" +
                "    <sources>\n" +
                "        <filter id=\"filter0\" regex=\"filter0\" includesFile=\"true\" includesFolder=\"true\" childValid=\"false\" />\n" +
                "        <filter id=\"filter1\" regex=\"filter1\" includesFile=\"true\" includesFolder=\"true\" childValid=\"false\" />\n" +
                "        <folder id=\"folderNode0\" name=\"folderNode0\">\n" +
                "            <filter id=\"filter0\" regex=\"filter0\" includesFile=\"true\" includesFolder=\"true\" childValid=\"false\" />\n" +
                "            <filter id=\"filter1\" regex=\"filter1\" includesFile=\"true\" includesFolder=\"true\" childValid=\"false\" />\n" +
                "            <file id=\"fileNode0\" name=\"fileNode0\" />\n" +
                "            <file id=\"fileNode1\" name=\"fileNode1\" />\n" +
                "            <folder id=\"folderNode0\" name=\"folderNode0\">\n" +
                "            </folder>\n" +
                "            <folder id=\"folderNode1\" name=\"folderNode1\">\n" +
                "            </folder>\n" +
                "        </folder>\n" +
                "        <folder id=\"folderNode1\" name=\"folderNode1\">\n" +
                "            <filter id=\"filter0\" regex=\"filter0\" includesFile=\"true\" includesFolder=\"true\" childValid=\"false\" />\n" +
                "        </folder>\n" +
                "        <file id=\"fileNode0\" name=\"fileNode0\" />\n" +
                "        <file id=\"fileNode1\" name=\"fileNode1\" />\n" +
                "    </sources>\n" +
                "    <output>\n" +
                "        <folder id=\"folderNode0\" name=\"folderNode0\">\n" +
                "            <out id=\"out0\" />\n" +
                "        </folder>\n" +
                "        <out id=\"out0\" />\n" +
                "        <out id=\"out1\" />\n" +
                "    </output>\n" +
                "</compressor-config>";
        ConfigConverter.convert(xml).ifPresent(System.out::println);
    }

    @Test
    void generate() {
        CompressorConfig compressorConfig = new CompressorConfig();
        Sources sources = new Sources();

        for (int i = 0; i < 5; i++) {
            sources.addFileNode(genrateFileNode(i));
            FolderNode folderNode = genrateFolderNode(i);
            for (int j = 0; j < 2; j++) {
                folderNode.addFileNode(genrateFileNode(j));
                folderNode.addFilter(genrateFilter(j));
                folderNode.addFolderNode(genrateFolderNode(j));
            }
            sources.addFolderNode(folderNode);
        }
        for (int i = 0; i < 2; i++) {
            sources.addFilter(genrateFilter(i));
        }
        compressorConfig.setSources(sources);

        Output output = new Output();
        compressorConfig.setOutput(output);
        for (int i = 0; i < 5; i++) {
            output.addOut(genrateOut(i));
        }
        for (int i = 0; i < 2; i++) {
            FolderNode folderNode = genrateFolderNode(i);
            output.addFolderNode(folderNode);
            for (int j = 0; j < 3; j++) {
                folderNode.addOut(genrateOut(j));
            }
        }
        System.out.println(ConfigConverter.generate(compressorConfig));
    }

    private FolderNode genrateFolderNode(int index) {
        FolderNode folderNode = new FolderNode();
        String name = "folderNode" + index;
        folderNode.setId(name);
        folderNode.setName(name);
        return folderNode;
    }

    private FileNode genrateFileNode(int index) {
        FileNode fileNode = new FileNode();
        String name = "fileNode" + index;
        fileNode.setId(name);
        fileNode.setName(name);
        return fileNode;
    }

    private Filter genrateFilter(int index) {
        Filter filter = new Filter();
        String name = "filter" + index;
        filter.setId(name);
        filter.setRegex(name);
        return filter;
    }

    public Out genrateOut(int index) {
        Out out = new Out();
        String name = "out" + index;
        out.setId(name);
        return out;
    }
}
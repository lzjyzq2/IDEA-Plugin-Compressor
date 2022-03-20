package cn.settile.lzjyzq2.compressor.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public final class Sources {

    @JacksonXmlProperty(isAttribute = true, localName = "src")
    private String src;

    @JacksonXmlProperty(localName = "filter")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Filter> filters;

    @JacksonXmlProperty(localName = "folder")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<FolderNode> folderNodes;


    @JacksonXmlProperty(localName = "file")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<FileNode> fileNodes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sources sources = (Sources) o;
        return Objects.equals(src, sources.src) && Objects.equals(filters, sources.filters) && Objects.equals(folderNodes, sources.folderNodes) && Objects.equals(fileNodes, sources.fileNodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(src, filters, folderNodes, fileNodes);
    }

    @Override
    public String toString() {
        return src;
    }


    public void addFilter(Filter filter) {
        if (filters == null) {
            filters = new LinkedList<>();
        }
        filter.setSources(this);
        filters.add(filter);
    }

    public void removeFilter(Filter filter) {
        if (filters != null) {
            filters.remove(filter);
        }
    }

    public void addFileNode(FileNode fileNode) {
        if (fileNodes == null) {
            fileNodes = new LinkedList<>();
        }
        fileNode.setSources(this);
        fileNodes.add(fileNode);
    }

    public void removeFileNode(FileNode fileNode) {
        if (fileNodes != null) {
            fileNodes.remove(fileNode);
        }
    }

    public void addFolderNode(FolderNode folderNode) {
        if (folderNodes == null) {
            folderNodes = new LinkedList<>();
        }
        folderNode.setSources(this);
        folderNodes.add(folderNode);
    }

    public void removeFolderNode(FolderNode folderNode) {
        if (folderNodes != null) {
            folderNodes.remove(folderNode);
        }
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
        filters.forEach(filter -> filter.setSources(this));
    }

    public List<FolderNode> getFolderNodes() {
        return folderNodes;
    }

    public void setFolderNodes(List<FolderNode> folderNodes) {
        this.folderNodes = folderNodes;
        folderNodes.forEach(folderNode -> folderNode.setSources(this));
    }

    public List<FileNode> getFileNodes() {
        return fileNodes;
    }

    public void setFileNodes(List<FileNode> fileNodes) {
        this.fileNodes = fileNodes;
        fileNodes.forEach(fileNode -> fileNode.setSources(this));
    }
}

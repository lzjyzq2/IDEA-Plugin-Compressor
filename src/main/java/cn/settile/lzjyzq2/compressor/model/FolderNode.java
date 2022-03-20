package cn.settile.lzjyzq2.compressor.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;


public class FolderNode {

    private Sources sources;

    private FolderNode parent;

    @JacksonXmlProperty(localName = "id", isAttribute = true)
    private String id;

    @JacksonXmlProperty(localName = "name", isAttribute = true)
    private String name;

    @JacksonXmlProperty(localName = "filter")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Filter> filters;

    @JacksonXmlProperty(localName = "file")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<FileNode> fileNodes;

    @JacksonXmlProperty(localName = "out")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Out> outs;

    @JacksonXmlProperty(localName = "folder")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<FolderNode> folderNodes;

    @Override
    public String toString() {
        return name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FolderNode that = (FolderNode) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(filters, that.filters) && Objects.equals(fileNodes, that.fileNodes) && Objects.equals(outs, that.outs) && Objects.equals(folderNodes, that.folderNodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, filters, fileNodes, outs, folderNodes);
    }

    public void addFilter(Filter filter) {
        if (filters == null) {
            filters = new LinkedList<>();
        }
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
        folderNodes.add(folderNode);
    }

    public void removeFolderNode(FolderNode folderNode) {
        if (folderNodes != null) {
            folderNodes.remove(folderNode);
        }
    }

    public void addOut(Out out) {
        if (outs == null) {
            outs = new LinkedList<>();
        }
        outs.add(out);
    }

    public void removeOut(Out out) {
        if (outs != null) {
            outs.remove(out);
        }
    }

    public Sources getSources() {
        return sources;
    }

    public void setSources(Sources sources) {
        this.sources = sources;
    }

    public FolderNode getParent() {
        return parent;
    }

    public void setParent(FolderNode parent) {
        this.parent = parent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
        filters.forEach(filter -> {
            filter.setSources(getSources());
            filter.setParent(this);
        });
    }

    public List<FileNode> getFileNodes() {
        return fileNodes;
    }

    public void setFileNodes(List<FileNode> fileNodes) {
        this.fileNodes = fileNodes;
        fileNodes.forEach(fileNode -> {
            fileNode.setSources(getSources());
            fileNode.setParent(this);
        });
    }

    public List<Out> getOuts() {
        return outs;
    }

    public void setOuts(List<Out> outs) {
        this.outs = outs;
    }

    public List<FolderNode> getFolderNodes() {
        return folderNodes;
    }

    public void setFolderNodes(List<FolderNode> folderNodes) {
        this.folderNodes = folderNodes;
        folderNodes.forEach(folderNode -> {
            folderNode.setSources(getSources());
            folderNode.setParent(this);
        });
    }
}

package cn.settile.lzjyzq2.compressor.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class Output {

    @JacksonXmlProperty(localName = "path", isAttribute = true)
    private String path;

    @JacksonXmlProperty(localName = "name", isAttribute = true)
    private String name;

    @JacksonXmlProperty(localName = "type", isAttribute = true)
    private String type;

    @JacksonXmlProperty(localName = "folder", isAttribute = true)
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<FolderNode> folderNodes;

    @JacksonXmlProperty(localName = "out", isAttribute = true)
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Out> outs;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Output output = (Output) o;
        return Objects.equals(path, output.path) && Objects.equals(name, output.name) && Objects.equals(type, output.type) && Objects.equals(folderNodes, output.folderNodes) && Objects.equals(outs, output.outs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, name, type, folderNodes, outs);
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

    @Override
    public String toString() {
        return "Output{" +
                "path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

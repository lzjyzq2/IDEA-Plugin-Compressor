package cn.settile.lzjyzq2.compressor.model;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.*;

import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Filter {

    private Sources sources;

    private FolderNode parent;

    @JacksonXmlProperty(localName = "id", isAttribute = true)
    private String id;

    /**
     * 默认过滤正则
     */
    @Builder.Default
    @JacksonXmlProperty(isAttribute = true, localName = "regex")
    private String regex = "*";

    @Builder.Default
    @JacksonXmlProperty(isAttribute = true, localName = "excludeRegex")
    private String excludeRegex = "";

    @Builder.Default
    @JacksonXmlProperty(isAttribute = true, localName = "includesFile")
    private boolean includesFile = true;

    @Builder.Default
    @JacksonXmlProperty(isAttribute = true, localName = "includesFolder")
    private boolean includesFolder = true;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filter filter = (Filter) o;
        return includesFile == filter.includesFile && includesFolder == filter.includesFolder && Objects.equals(id, filter.id) && Objects.equals(regex, filter.regex) && Objects.equals(excludeRegex, filter.excludeRegex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, regex, excludeRegex, includesFile, includesFolder);
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

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public boolean isIncludesFile() {
        return includesFile;
    }

    public void setIncludesFile(boolean includesFile) {
        this.includesFile = includesFile;
    }

    public boolean isIncludesFolder() {
        return includesFolder;
    }

    public void setIncludesFolder(boolean includesFolder) {
        this.includesFolder = includesFolder;
    }

    public String getExcludeRegex() {
        return excludeRegex;
    }

    public void setExcludeRegex(String excludeRegex) {
        this.excludeRegex = excludeRegex;
    }
}

package cn.settile.lzjyzq2.compressor.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JacksonXmlRootElement(localName = "compressor-config")
public final class CompressorConfig {

    @JacksonXmlProperty(localName = "tmpDir")
    private String tmpDir;

    @JacksonXmlProperty(localName = "sources")
    private Sources sources;

    @JacksonXmlProperty(localName = "output")
    private Output output;

    @Override
    public String toString() {
        return "CompressorConfig{" +
                "sources=" + sources +
                ", output=" + output +
                '}';
    }
}

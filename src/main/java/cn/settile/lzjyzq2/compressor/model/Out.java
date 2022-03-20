package cn.settile.lzjyzq2.compressor.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Out {

    @JacksonXmlProperty(localName = "id", isAttribute = true)
    private String id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Out out = (Out) o;
        return Objects.equals(id, out.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

package cn.settile.lzjyzq2.compressor.util;

import cn.settile.lzjyzq2.compressor.model.CompressorConfig;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class ConfigConverter {


    public static Optional<CompressorConfig> convert(String xml) {
        try {
            return Optional.of(XmlUtil.newInstance().xml2Object(xml, CompressorConfig.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static Optional<CompressorConfig> convert(File file) {
        return XmlUtil.newInstance().xml2Object(file, CompressorConfig.class);
    }

    public static String generate(@Nonnull CompressorConfig compressorConfig) {
        try {
            return XmlUtil.defaultInstance().object2Xml(compressorConfig);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

}

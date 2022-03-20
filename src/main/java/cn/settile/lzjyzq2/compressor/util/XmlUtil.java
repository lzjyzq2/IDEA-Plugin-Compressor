package cn.settile.lzjyzq2.compressor.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public class XmlUtil {

    /**
     * 序列化用Mapper
     */
    private XmlMapper objectMapper;

    /**
     * 隐藏构造方法
     */
    private XmlUtil() {
        objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 返回使用默认配置的XmlUtil
     * 该配置的XmlUtil序列化时输出头部XML声明
     *
     * @return 默认配置的XmlUtil
     */
    public static XmlUtil defaultInstance() {
        XmlUtil xmlUtils = new XmlUtil();
        xmlUtils.getObjectMapper().configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
        return xmlUtils;
    }

    /**
     * 获取新的XmlUtil实例
     *
     * @return 新的XmlUtil实例
     */
    public static XmlUtil newInstance() {
        return new XmlUtil();
    }

    /**
     * 获取序列化使用的XmlMapper
     *
     * @return 序列化使用的XmlMapper
     */
    public XmlMapper getObjectMapper() {
        return objectMapper;
    }

    /**
     * 设置序列化使用的XmlMapper
     *
     * @param objectMapper xmlMapper
     */
    public void setObjectMapper(XmlMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * 对象转换为XML
     *
     * @param o 待转换对象
     * @return 转换后XML
     * @throws JsonProcessingException 转换异常
     */
    public String object2Xml(Object o) throws JsonProcessingException {
        return objectMapper.writeValueAsString(o);
    }

    /**
     * 反序列化XML为Object
     *
     * @param xml   源XML
     * @param clazz 目标类型
     * @param <T>   目标类型泛型
     * @return 转换后对象
     * @throws IOException 异常
     */
    public <T> T xml2Object(String xml, Class<T> clazz) throws IOException {
        return objectMapper.readValue(xml, clazz);
    }

    /**
     * 从输入流中反序列化对象
     *
     * @param inputStream {@code XML}文件输入流
     * @param clazz       目标类型
     * @param <T>         目标类型泛型
     * @return 反序列化结果
     * @throws IOException IO异常
     */
    public <T> T xml2Object(InputStream inputStream, Class<T> clazz) throws IOException {
        return objectMapper.readValue(inputStream, clazz);
    }

    /**
     * 从文件中反序列化对象
     *
     * @param file  XML源文件
     * @param clazz 目标类型
     * @param <T>   目标类型泛型
     * @return 反序列化结果
     */
    public <T> Optional<T> xml2Object(File file, Class<T> clazz) {
        try {
            return Optional.of(objectMapper.readValue(file, clazz));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}

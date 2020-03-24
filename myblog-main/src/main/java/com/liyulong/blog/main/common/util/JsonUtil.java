package com.liyulong.blog.main.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * json工具类，
 * 将json字符串转化为对象
 * 将对象转化为json字符串
 */
@Slf4j
public class JsonUtil {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 使用泛型方法，将json字符串转换为相应的javabean对象
     * 1.转换为普通javabean：readvalue(json,Student.class)
     * 2.转换为List，如List<Student>,将第二个参数传递为Student
     * [].class,然后使用Arrays.asList();方法将得到的数组转换为特定类型的List
     * @param jsonStr
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> T toObj(String jsonStr,Class<T> valueType){
        //确定强制费数组是否可接受的功能
        //如果启用，集合反序列化将尝试处理非数组
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY,true);
        try {
            return objectMapper.readValue(jsonStr,valueType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json数组转为List
     * @param jsonStr
     * @param valueTypRef
     * @param <T>
     * @return
     */
    public static <T> T toList(String jsonStr, TypeReference<T> valueTypRef){
        try {
            return objectMapper.readValue(jsonStr,valueTypRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将javabean转为json字符串
     * @param object
     * @return
     */
    public static String toJson(Object object){
        if(object instanceof Integer || object instanceof Long || object instanceof Float ||
                object instanceof Double || object instanceof Boolean || object instanceof String){
            return String.valueOf(object);
        }
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}

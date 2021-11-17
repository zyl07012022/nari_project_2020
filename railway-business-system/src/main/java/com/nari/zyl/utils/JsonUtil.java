package com.nari.zyl.utils;

import java.io.IOException;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class JsonUtil {
	
	private static ObjectMapper objectMapper = new ObjectMapper();

	 /** 
     * 将对象序列化为JSON字符串 
     *  
     * @param object 
     * @return JSON字符串 
     */
    public static String serialize(Object object) {  
    	String rtn  = null;
    	try {
			rtn = objectMapper.writeValueAsString(object);
    	} catch (JsonParseException e) {  
        	e.printStackTrace();
        } catch (JsonMappingException e) {  
        	e.printStackTrace();  
        } catch (IOException e) {  
        	e.printStackTrace(); 
        }  
    	return rtn;
//    	ValueFilter valueFilter = new ValueFilter() {
//
//            @Override
//            public Object process(Object object, String name, Object value) {
//                if (value == null)
//                    return null;
//                return value;
//            }
//        };
//        return JSONObject.toJSONString(object,valueFilter); 
    }  
  
    /** 
     * 将JSON字符串反序列化为对象 
     *  
     * @param object 
     * @return JSON字符串 
     */  
    public static <T> T deserialize(String json, Class<T> clazz) {  
        Object object = null;  
        try {  
            object = objectMapper.readValue(json, TypeFactory.rawClass(clazz));  
        } catch (JsonParseException e) {  
        	e.printStackTrace();
        } catch (JsonMappingException e) {  
        	e.printStackTrace();  
        } catch (IOException e) {  
        	e.printStackTrace(); 
        }  
        return (T) object;  
    }  
  
    /** 
     * 将JSON字符串反序列化为对象 
     *  
     * @param object 
     * @return JSON字符串 
     */  
    public static <T> T deserialize(String json, TypeReference<T> typeRef) {  
        try {  
            return (T) objectMapper.readValue(json, typeRef);  
        } catch (JsonParseException e) {  
        	e.printStackTrace(); 
        } catch (JsonMappingException e) {  
        	e.printStackTrace();
        } catch (IOException e) {  
        	e.printStackTrace();
        }  
        return null;  
    }  
    
    /**
     * 根据字段名生成json字符串
     */
    public static String seriableByparam(Object object,String paramName){
    	 SimplePropertyPreFilter filter = new SimplePropertyPreFilter(object.getClass(), paramName);
    	 String jsonStu =JSON.toJSONString(object,filter);
    	 return jsonStu;
    }
    
    /**
     * json 转 List<T>
     */
    public static <T> List<T> jsonToList(String jsonString, Class<T> clazz) {
        @SuppressWarnings("unchecked")
        List<T> ts = (List<T>) JSONArray.parseArray(jsonString, clazz);
        return ts;
    }
    
    /**
     * List<T> 转 json 
     */
    public static <T> String listToJson(List<T> ts) {
        String jsons = JSON.toJSONString(ts);
        return jsons;
    }

} 
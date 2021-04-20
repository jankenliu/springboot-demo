package com.jankin.springboot.demo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author lyy
 * @date 2021/1/7 3:13 下午
 */
public class ToolUtilTest {


    @Test
    public void getUserName() throws  Exception{

        List<String> strings = JSONObject.parseArray("[\"987\",\"654\"]\n", String.class);
        System.out.println("strings = " + strings);
    }

    /**
     * JSON字符串排序
     * @param json 原JSON字符串
     * @return 排序后的JSON字符串
     */
    public static String getMapSortFieldJson(String json) {
        if (StringUtils.isEmpty(json)) {
            return json;
        }
        Object object = JSONObject.parse(json);
        if (null != object && object instanceof JSONObject) {
            JSONObject jsonObject = JSONObject.parseObject(json);
            json = getMapSortObjectJson(jsonObject);
        }
        if (null != object && object instanceof JSONArray) {
            JSONArray jsonArray = JSONArray.parseArray(json);
            json = getMapSortArrayJson(jsonArray);
        }
        return json;
    }


    /**
     * JSONArray转换为排序后的字符串
     * @param jsonArray  * @return
     *                   
     */
    private static String getMapSortArrayJson(JSONArray jsonArray) {
        JSONArray resultArray = new JSONArray();
        for (int index = 0; index < jsonArray.size(); index++) {
            //3、把里面的对象转化为JSONObject
            String value = jsonArray.getString(index);
            if (JSONObject.isValidObject(value) || JSONObject.isValidArray(value)) {
                resultArray.add(JSONObject.parse(getMapSortFieldJson(value)));
                continue;
            }
            if (JSONObject.isValid(value)) {
                resultArray.add(jsonArray.get(index));
                continue;
            }
            resultArray.add(value);
        }
        return JSONArray.toJSONString(resultArray, SerializerFeature.MapSortField);
    }


    /**
     * JSONObject转换为排序后的字符串
     * @param jsonObject  * @return
     *                    
     */
    private static String getMapSortObjectJson(JSONObject jsonObject) {
        if (null == jsonObject) {
            return "";
        }
        Set<String> keySet = jsonObject.keySet();
        Iterator<String> keys = keySet.iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            String value = jsonObject.getString(key);
            //如果不是JSONArray或JSONObject，则跳过
            if (!JSONObject.isValid(value)) {
                continue;
            }
            Object object = JSONObject.parse(value);
            if (null != object && object instanceof JSONObject) {
                JSONObject valueJsonObject = JSONObject.parseObject(value);
                String jsonValue = getMapSortObjectJson(valueJsonObject);
                jsonObject.fluentPut(key, JSONObject.parseObject(jsonValue));
            }
            if (null != object && object instanceof JSONArray) {
                JSONArray jsonArray = JSONArray.parseArray(value);
                String jsonValue = getMapSortArrayJson(jsonArray);
                jsonObject.fluentPut(key, JSONArray.parseArray(jsonValue));
            }
        }
        return JSONObject.toJSONString(jsonObject, SerializerFeature.MapSortField);
    }

}

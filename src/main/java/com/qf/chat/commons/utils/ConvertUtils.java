package com.qf.chat.commons.utils;

import org.apache.commons.beanutils.BeanMap;

import java.util.Map;

import static org.apache.commons.beanutils.BeanUtils.populate;

public class ConvertUtils {
    //1、map转换为object

    public static Object mapToObject(Map<String, Object> map, Class<?> cla) throws Exception {
        if (map == null){
            return null;
        }
        Object obj = cla.newInstance();
        populate(obj, map);
        return obj;
    }

    //2、object转换为map
    public static Map<?, ?> objectToMap(Object obj) {
        if (obj == null)
            return null;
        return new BeanMap(obj);
    }

}

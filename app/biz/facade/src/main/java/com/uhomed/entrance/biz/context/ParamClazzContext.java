package com.uhomed.entrance.biz.context;

import java.util.HashMap;
import java.util.Map;

/**
 * @author
 * @version $$Id: , v 0.1    Exp $$
 */
public class ParamClazzContext {


    private static Map<String,String> clazzMap ;

    static {
        clazzMap = new HashMap<>();
        clazzMap.put("String","java.lang.String");
        clazzMap.put("Int","java.lang.Integer");
        clazzMap.put("Float","java.lang.Float");
        clazzMap.put("Double","java.lang.Double");
        clazzMap.put("Long","java.lang.Long");
        clazzMap.put("Boolean","java.lang.Boolean");
        clazzMap.put("Date","java.util.Date");
        clazzMap.put("List","java.util.List");
        clazzMap.put("Object","java.lang.Object");
        clazzMap.put("Enum","java.lang.Enum");
        clazzMap.put("RequestBody","java.lang.String");
        clazzMap.put("ParameterMap","java.util.Map");
    }

    public static String getClazz(String type){

        String clazz = clazzMap.get(type);
        if(clazz == null) throw new NullPointerException("类别未找到");
        return clazz;
    }

}

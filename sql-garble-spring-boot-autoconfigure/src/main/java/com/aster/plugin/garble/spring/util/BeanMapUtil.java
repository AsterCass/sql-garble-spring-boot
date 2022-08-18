package com.aster.plugin.garble.spring.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class BeanMapUtil {


    /**
     * bean to map
     */
    public static <T> Map<String, Object> beanToMap(Object object, Class<T> clazz) {
        try {
            Map<String, Object> map = new HashMap<>();
            Class<?> classType = Class.forName(clazz.getName());
            Field[] fields = classType.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(object));
            }
            return map;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new HashMap<>();
        }

    }

    /**
     * map to bean
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<T> beanClass) {
        try {
            T object = beanClass.getDeclaredConstructor().newInstance();
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                if (map.containsKey(field.getName())) {
                    field.set(object, map.get(field.getName()));
                }
            }
            return object;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


}

package com.uhomed.entrance.core.utils;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author
 * @version $$Id: , v 0.1    Exp $$
 */
public class BeanUtil {

    public static <V, P> List<P> convertList(List<V> baseList, Class<P> target) {
        if (baseList == null) {
            return null;
        } else {
            List<P> targetList = new ArrayList<P>();
            for (V vo : baseList) {
                try {
                    Object o = target.newInstance();
                    com.xiaoleilu.hutool.util.BeanUtil.copyProperties(vo,o);
                    targetList.add((P)o );
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return targetList;
        }
    }

}

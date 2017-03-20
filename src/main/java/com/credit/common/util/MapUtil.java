package com.credit.common.util;

import java.util.*;

/**
 * Created by wangjunling on 2017/3/10.
 */
public class MapUtil
{
    public static <K, V> Map<K, V> sort(Map<K, V> map, Comparator<Map.Entry<K, V>> comparator)
    {
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, comparator);

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list)
        {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map)
    {
        return sort(map, new Comparator<Map.Entry<K, V>>()
        {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

    }
}

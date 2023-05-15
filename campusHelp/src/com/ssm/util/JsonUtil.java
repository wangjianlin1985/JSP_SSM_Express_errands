// 
// 
// 

package com.ssm.util;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.beans.PropertyDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.Set;
import java.util.Map;
import java.util.List;
import java.math.BigInteger;
import java.math.BigDecimal;

public class JsonUtil
{
    public static String object2json(final Object obj) {
        final StringBuilder json = new StringBuilder();
        if (obj == null) {
            json.append("\"\"");
        }
        else if (obj instanceof String || obj instanceof Integer || obj instanceof Float || obj instanceof Boolean || obj instanceof Short || obj instanceof Double || obj instanceof Long || obj instanceof BigDecimal || obj instanceof BigInteger || obj instanceof Byte) {
            json.append("\"").append(string2json(obj.toString())).append("\"");
        }
        else if (obj instanceof Object[]) {
            json.append(array2json((Object[])obj));
        }
        else if (obj instanceof List) {
            json.append(list2json((List<?>)obj));
        }
        else if (obj instanceof Map) {
            json.append(map2json((Map<?, ?>)obj));
        }
        else if (obj instanceof Set) {
            json.append(set2json((Set<?>)obj));
        }
        else {
            json.append(bean2json(obj));
        }
        return json.toString();
    }
    
    public static String bean2json(final Object bean) {
        final StringBuilder json = new StringBuilder();
        json.append("{");
        PropertyDescriptor[] props = null;
        try {
            props = Introspector.getBeanInfo(bean.getClass(), Object.class).getPropertyDescriptors();
        }
        catch (IntrospectionException ex) {}
        if (props != null) {
            for (int i = 0; i < props.length; ++i) {
                try {
                    final String name = object2json(props[i].getName());
                    final String value = object2json(props[i].getReadMethod().invoke(bean, new Object[0]));
                    json.append(name);
                    json.append(":");
                    json.append(value);
                    json.append(",");
                }
                catch (Exception ex2) {}
            }
            json.setCharAt(json.length() - 1, '}');
        }
        else {
            json.append("}");
        }
        return json.toString();
    }
    
    public static String list2json(final List<?> list) {
        final StringBuilder json = new StringBuilder();
        json.append("[");
        if (list != null && list.size() > 0) {
            for (final Object obj : list) {
                json.append(object2json(obj));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        }
        else {
            json.append("]");
        }
        return json.toString();
    }
    
    public static String array2json(final Object[] array) {
        final StringBuilder json = new StringBuilder();
        json.append("[");
        if (array != null && array.length > 0) {
            for (final Object obj : array) {
                json.append(object2json(obj));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        }
        else {
            json.append("]");
        }
        return json.toString();
    }
    
    public static String map2json(final Map<?, ?> map) {
        final StringBuilder json = new StringBuilder();
        json.append("{");
        if (map != null && map.size() > 0) {
            for (final Object key : map.keySet()) {
                json.append(object2json(key));
                json.append(":");
                json.append(object2json(map.get(key)));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, '}');
        }
        else {
            json.append("}");
        }
        return json.toString();
    }
    
    public static String set2json(final Set<?> set) {
        final StringBuilder json = new StringBuilder();
        json.append("[");
        if (set != null && set.size() > 0) {
            for (final Object obj : set) {
                json.append(object2json(obj));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        }
        else {
            json.append("]");
        }
        return json.toString();
    }
    
    public static String string2json(final String s) {
        if (s == null) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            final char ch = s.charAt(i);
            switch (ch) {
                case '\"': {
                    sb.append("\\\"");
                    break;
                }
                case '\\': {
                    sb.append("\\\\");
                    break;
                }
                case '\b': {
                    sb.append("\\b");
                    break;
                }
                case '\f': {
                    sb.append("\\f");
                    break;
                }
                case '\n': {
                    sb.append("\\n");
                    break;
                }
                case '\r': {
                    sb.append("\\r");
                    break;
                }
                case '\t': {
                    sb.append("\\t");
                    break;
                }
                case '/': {
                    sb.append("\\/");
                    break;
                }
                default: {
                    if (ch >= '\0' && ch <= '\u001f') {
                        final String ss = Integer.toHexString(ch);
                        sb.append("\\u");
                        for (int k = 0; k < 4 - ss.length(); ++k) {
                            sb.append('0');
                        }
                        sb.append(ss.toUpperCase());
                        break;
                    }
                    sb.append(ch);
                    break;
                }
            }
        }
        return sb.toString();
    }
}

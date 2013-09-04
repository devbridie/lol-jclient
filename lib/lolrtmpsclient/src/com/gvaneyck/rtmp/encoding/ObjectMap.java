package com.gvaneyck.rtmp.encoding;

import java.util.Date;
import java.util.HashMap;

/**
 * A map of objects with utility methods
 * 
 * @author Gabriel Van Eyck
 */
public class ObjectMap extends HashMap<String, Object> {
    private static final long serialVersionUID = -7957187649383807122L;

    /**
     * Convenience for going through object hierarchy
     * 
     * @param key The key of the ObjectMap
     * @return The ObjectMap
     */
    public ObjectMap getMap(String key) {
        return (ObjectMap)get(key);
    }

    /**
     * Convenience for retrieving Strings
     * 
     * @param key The key of the String
     * @return The String
     */
    public String getString(String key) {
        return (String)get(key);
    }

    /**
     * Convenience for retrieving integers
     * 
     * @param key The key of the integer
     * @return The integer
     */
    public Integer getInt(String key) {
        Object val = get(key);
        if (val == null)
            return null;
        else if (val instanceof Integer)
            return (Integer)val;
        else
            return ((Double)val).intValue();
    }

    /**
     * Convenience for retrieving longs
     * 
     * @param key The key of the long
     * @return The long
     */
    public Long getLong(String key) {
        Object val = get(key);
        if (val == null)
            return null;
        else if (val instanceof Integer)
            return ((Integer)val).longValue();
        else
            return ((Double)val).longValue();
    }
    
    /**
     * Convenience for retrieving doubles
     * 
     * @param key The key of the double
     * @return The double
     */
    public Double getDouble(String key) {
        Object val = get(key);
        if (val == null)
            return null;
        else if (val instanceof Double)
            return (Double)val;
        else
            return ((Integer)val).doubleValue();
    }

    /**
     * Convenience for retrieving booleans
     * 
     * @param key The key of the boolean
     * @return The boolean
     */
    public Boolean getBool(String key) {
        return (Boolean)get(key);
    }

    /**
     * Convenience for retrieving object arrays
     * 
     * @param key The key of the object array
     * @return The object array
     */
    public Object[] getArray(String key) {
        return (Object[])get(key);
    }

    /**
     * Convenience for retrieving Date objects
     * 
     * @param key The key of the Date object
     * @return The Date object
     */
    public Date getDate(String key) {
        return (Date)get(key);
    }
}

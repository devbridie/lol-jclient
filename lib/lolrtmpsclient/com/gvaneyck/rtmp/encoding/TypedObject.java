package com.gvaneyck.rtmp.encoding;

import java.util.regex.Pattern;

/**
 * A map that has a type, used to represent an object
 * 
 * @author Gabriel Van Eyck
 */
public class TypedObject extends ObjectMap {
    private static final long serialVersionUID = 1244827787088018807L;

    private static Pattern linePattern = Pattern.compile("^", Pattern.MULTILINE);

    public String type;

    /**
     * Creates a typed object that is simply a map (null type)
     */
    public TypedObject() {
        this.type = null;
    }

    /**
     * Initializes the type of the object, null type implies a dynamic class
     * (used for headers and some other things)
     * 
     * @param type The type of the object
     */
    public TypedObject(String type) {
        this.type = type;
    }

    /**
     * Creates a TypedObject from an ObjectMap
     * 
     * @param data
     */
    public TypedObject(ObjectMap data) {
        this.type = null;
        this.putAll(data);
    }

    /**
     * Creates a flex.messaging.io.ArrayCollection in the structure that the
     * encoder expects
     * 
     * @param data The data for the ArrayCollection
     * @return
     */
    public static TypedObject makeArrayCollection(Object[] data) {
        TypedObject ret = new TypedObject("flex.messaging.io.ArrayCollection");
        ret.put("array", data);
        return ret;
    }

    /**
     * Convenience for going through object hierarchy
     * 
     * @param key The key of the TypedObject
     * @return The TypedObject
     */
    public TypedObject getTO(String key) {
        return (TypedObject)get(key);
    }

    /**
     * Convenience for retrieving object arrays
     * Also handles flex.messaging.io.ArrayCollection
     * 
     * @param key The key of the object array
     * @return The object array
     */
    public Object[] getArray(String key) {
        if (get(key) instanceof TypedObject && getTO(key).type.equals("flex.messaging.io.ArrayCollection"))
            return (Object[])getTO(key).get("array");
        else
            return (Object[])get(key);
    }

    public String toString() {
        StringBuilder buff = new StringBuilder();
        buff.append("{");
        for (String key : keySet()) {
            buff.append(key);
            buff.append('=');
            if (key.equals("array")) {
                buff.append('[');
                for (Object o : getArray(key)) {
                    buff.append(o.toString());
                    buff.append(", ");
                }
                buff.append(']');
            }
            else if (get(key) instanceof Double) {
                buff.append(((Double)get(key)).longValue());
            }
            else
                buff.append(get(key));
            buff.append(", ");
        }
        buff.append("}");
        return buff.toString();
    }

    /**
     * Makes a pretty (indented) human readable form of this object
     * 
     * @return A pretty string
     */
    public String toPrettyString() {
        String[] keys = keySet().toArray(new String[0]);
        if (keys.length == 0)
            return "{ }\n";

        StringBuilder buff = new StringBuilder();

        buff.append("{\n");
        for (int i = 0; i < keys.length; i++) {
            String key = keys[i];

            if (key.equals("array"))
                buff.append(indent(arrayToString(getArray(key))));
            else if (get(key) instanceof Object[])
            	buff.append(indent(arrayToString((Object[])get(key))));
            else if (get(key) == null) {
                buff.append("    ");
                buff.append(key);
                buff.append("=null");
            }
            else if (get(key) instanceof Double) {
                buff.append("    ");
                buff.append(key);
                buff.append('=');
                buff.append(((Double)get(key)).longValue());
            }
            else if (get(key) instanceof TypedObject)
            	buff.append(indent(key + '=' + ((TypedObject)get(key)).toPrettyString()));
            else
                buff.append(indent(key + '=' + get(key).toString()));

            if (i < keys.length - 1)
                buff.append(",\n");
        }
        buff.append("\n}\n");

        return buff.toString();
    }

    /**
     * Turns an array into a pretty string
     * 
     * @param array The array to transform
     * @return A pretty string
     */
    private String arrayToString(Object[] array) {
        if (array.length == 0)
            return "[ ]\n";

        StringBuilder buff = new StringBuilder();

        buff.append("[\n");
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null)
                buff.append("    null");
            else if (array[i] instanceof TypedObject)
            	buff.append(indent(((TypedObject)array[i]).toPrettyString()));
            else
                buff.append(indent(array[i].toString()));

            if (i < array.length - 1)
                buff.append(",\n");
        }
        buff.append("\n]\n");

        return buff.toString();
    }

    /**
     * Indents some text
     * 
     * @param data The text to indent
     * @return Indented text
     */
    private String indent(String data) {
        return linePattern.matcher(data.trim()).replaceAll("    ");
    }
}

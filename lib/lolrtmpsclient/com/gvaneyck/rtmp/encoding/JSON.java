package com.gvaneyck.rtmp.encoding;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Simple JSON parser. Throws a RuntimeException on parsing error
 * 
 * @author Gabriel Van Eyck
 */
public class JSON {
    /**
     * Parses JSON from a string
     * 
     * @param json The JSON to parse
     * @return The parsed object
     */
    public static Object parse(String json) {
        if (json == null)
            return null;

        LinkedList<Character> buff = new LinkedList<Character>();
        for (int i = 0; i < json.length(); i++)
            buff.add(json.charAt(i));
        return parse(buff);
    }

    /**
     * Parses JSON from a linked list
     * 
     * @param json The JSON to parse
     * @return The parsed object
     */
    private static Object parse(LinkedList<Character> json) {
        Character c = removeNonSpace(json);
        switch (c) {
        case '{':
            return parseObject(json);

        case '[':
            return parseArray(json);

        case '"':
            return parseString(json);

        default:
            json.addFirst(c);
            return parseOther(json);
        }
    }

    /**
     * Parses a JSON object
     * Assumes the opening curly brace has been consumed
     * 
     * @param json The JSON to parse
     * @return The parsed object as a map
     */
    private static ObjectMap parseObject(LinkedList<Character> json) {
        ObjectMap ret = new ObjectMap();

        // Check for empty objects
        Character c = removeNonSpace(json);
        if (c == '}')
            return ret;
        json.addFirst(c);

        do {
            c = removeNonSpace(json);
            if (c != '"')
                throw new JSONParsingException("JSON object member name did not start with a double quote: '" + c + "'");
            String key = parseString(json);

            c = removeNonSpace(json);
            if (c != ':')
                throw new JSONParsingException("JSON object missing colon for value or member: '" + c + "'");
            Object val = parse(json);

            ret.put(key, val);
        } while ((c = removeNonSpace(json)) == ',');

        if (c != '}')
            throw new JSONParsingException("JSON object did not end with a curly brace: '" + c + "'");

        return ret;
    }

    /**
     * Parses a JSON array
     * Assumes the opening square bracket has been consumed
     * 
     * @param json The JSON to parse
     * @return The parsed array
     */
    private static Object[] parseArray(LinkedList<Character> json) {
        // Check for empty array
        Character c = removeNonSpace(json);
        if (c == ']')
            return new Object[0];
        json.addFirst(c);

        ArrayList<Object> temp = new ArrayList<Object>();

        do {
            temp.add(parse(json));
            c = removeNonSpace(json);
        } while (c == ',');

        if (c != ']')
            throw new JSONParsingException("JSON array did not end with a square bracket: '" + c + "'");

        Object[] ret = new Object[temp.size()];
        for (int i = 0; i < temp.size(); i++)
            ret[i] = temp.get(i);

        return ret;
    }

    /**
     * Parses a JSON string
     * Assumes the opening double quote has been consumed
     * 
     * @param json The JSON to parse
     * @return The parsed string
     */
    private static String parseString(LinkedList<Character> json) {
        boolean postBackslash = false;
        StringBuilder buff = new StringBuilder();

        Character c;
        while ((c = json.removeFirst()) != '"' || postBackslash) {
            if (!postBackslash) {
                if (c == '\\')
                    postBackslash = true;
                else
                    buff.append(c);
            }
            else {
                switch (c) {
                case '"':
                    buff.append('"');
                    break;

                case '\\':
                    buff.append('\\');
                    break;

                case '/':
                    buff.append('/');
                    break;

                case 'b':
                    buff.append('\b');
                    break;

                case 'f':
                    buff.append('\f');
                    break;

                case 'n':
                    buff.append('\n');
                    break;

                case 'r':
                    buff.append('\r');
                    break;

                case 't':
                    buff.append('\t');
                    break;

                case 'u':
                    StringBuilder temp = new StringBuilder();
                    for (int i = 0; i < 4; i++)
                        temp.append(json.removeFirst());
                    buff.append((char)Integer.parseInt(temp.toString(), 16));
                    break;
                }

                postBackslash = false;
            }
        }

        return buff.toString();
    }

    /**
     * Parses JSON numbers, booleans, and null
     * 
     * @param json The JSON to parse
     * @return The parsed object
     */
    private static Object parseOther(LinkedList<Character> json) {
        Character c = removeNonSpace(json);
        switch (c) {
        case 't':
            for (int i = 0; i < 3; i++)
                json.removeFirst();
            return true;

        case 'f':
            for (int i = 0; i < 4; i++)
                json.removeFirst();
            return false;

        case 'n':
            for (int i = 0; i < 3; i++)
                json.removeFirst();
            return null;

        default:
            StringBuilder buff = new StringBuilder();
            boolean isInt = true;
            while (c == '-' || c >= '0' && c <= '9' || c == '.') {
                if (c == '.')
                    isInt = false;
                buff.append(c);
                c = json.removeFirst();
            }
            json.addFirst(c);

            if (buff.length() == 0)
                throw new JSONParsingException("Tried parsing number but got: '" + c + "'");

            if (isInt)
                return Integer.parseInt(buff.toString());
            else
                return Double.parseDouble(buff.toString());
        }
    }

    /**
     * Extracts the first non-whitespace character from the JSON string
     * 
     * @param json The JSON string
     * @return The first non-whitespace character
     */
    private static Character removeNonSpace(LinkedList<Character> json) {
        Character ret;
        while (Character.isWhitespace(ret = json.removeFirst())) {}
        return ret;
    }
}

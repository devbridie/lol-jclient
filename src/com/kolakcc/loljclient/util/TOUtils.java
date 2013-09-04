package com.kolakcc.loljclient.util;

import com.gvaneyck.rtmp.encoding.TypedObject;

public class TOUtils {
	public static TypedObject[] ArrayToTOArray(Object[] array) {
		TypedObject[] ret = new TypedObject[array.length];
		for (int i = 0; i < array.length; i++) {
			ret[i] = (TypedObject) array[i];
		}
		return ret;
	}
}

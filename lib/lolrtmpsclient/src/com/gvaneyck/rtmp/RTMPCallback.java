package com.gvaneyck.rtmp;

import com.gvaneyck.rtmp.encoding.TypedObject;

/**
 * Provides callback functionality
 * 
 * @author Gabriel Van Eyck
 */
public interface RTMPCallback {
    /**
     * The function to call after the result has been read
     * 
     * @param result The result for this callback
     */
    public void callback(TypedObject result);
}

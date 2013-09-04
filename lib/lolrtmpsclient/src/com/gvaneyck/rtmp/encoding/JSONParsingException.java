package com.gvaneyck.rtmp.encoding;

public class JSONParsingException extends RuntimeException {
    private static final long serialVersionUID = -7044042769876396807L;

    public JSONParsingException() {
        super();
    }

    public JSONParsingException(String message) {
        super(message);
    }
}

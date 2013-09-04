package com.gvaneyck.rtmp;

/**
 * A simple packet structure for PacketReader
 */
public class Packet {
    private byte[] dataBuffer;
    private int dataPos;
    private int dataSize;
    private int messageType;

    public void setSize(int size) {
        dataSize = size;
        dataBuffer = new byte[dataSize];
    }

    public void setType(int type) {
        messageType = type;
    }

    public void add(byte b) {
        dataBuffer[dataPos++] = b;
    }

    public boolean isComplete() {
        return (dataPos == dataSize);
    }

    public int getSize() {
        return dataSize;
    }

    public int getType() {
        return messageType;
    }

    public byte[] getData() {
        return dataBuffer;
    }
}

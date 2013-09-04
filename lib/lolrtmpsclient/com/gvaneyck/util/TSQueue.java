package com.gvaneyck.util;

import java.util.concurrent.Semaphore;

/**
 * A thread safe queue with automatic resizing
 * Supports multiple readers and multiple writers
 * 
 * @author Gvaneyck
 */
public class TSQueue<T> {
    public static final double EXPANSION_FACTOR = 1.5;

    private int front;
    private int back;
    private int size;
    private Object[] data;

    // Semaphore rather than Lock since it needs to be released from a separate
    // thread
    Semaphore reading = new Semaphore(1);
    Semaphore writing = new Semaphore(1);

    /**
     * Creates a queue with size 10
     */
    public TSQueue() {
        this(10);
    }

    /**
     * Creates a queue with the specified starting size
     * 
     * @param size
     */
    public TSQueue(int size) {
        this.size = size;
        data = new Object[size];
    }

    /**
     * Gets the object at the front of the queue or null if the queue is empty
     * 
     * @return
     */
    public T get() {
        reading.acquireUninterruptibly();

        if (front == back) {
            reading.release();
            return null;
        }

        Object ret = data[front % size];
        front++;

        reading.release();

        return (T)ret;
    }

    /**
     * Adds an object to the end of the queue
     * 
     * @param val
     */
    public void put(T val) {
        writing.acquireUninterruptibly();

        if (back == front + size)
            reallocate();
        data[back % size] = val;
        back++;

        writing.release();
    }

    /**
     * Expands the current array and copies over the old data
     * Note that the write lock is already acquired at this stage
     */
    private void reallocate() {
        reading.acquireUninterruptibly();

        final int oldFront = front;
        final int oldBack = back;
        final int oldSize = size;
        final Object[] oldData = data;

        front = 0;
        back = size;
        size = (int)(EXPANSION_FACTOR * size);
        data = new Object[size];

        // Block gets (and reallocating), but don't block puts while copying the
        // data back
        new Thread() {
            public void run() {
                int j = 0;
                for (int i = oldFront; i < oldBack; i++, j++)
                    data[j] = oldData[i % oldSize];
                reading.release();
            }
        }.start();
    }
}

package com.acme.edu;

import java.net.ServerSocket;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by vanbkin on 05.09.2017.
 */
public class LoggerServer implements Runnable {
    private final AtomicBoolean stopped = new AtomicBoolean();
    private ServerSocket serverSocket;

    @Override
    public void run() {
        while (!stopped.get()) {

        }
    }


}

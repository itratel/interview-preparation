package com.whd.interview.preparation.network.nio;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;

/**
 * @author whd.java@gmail.com
 * @date 2018/11/24 2:30
 * @apiNote Describe the function of this class in one sentence
 **/
public class NIOServer {


    /***
     * 只有一个主线程 Channel(server, client) Selector Buffer
     * @param args
     */
    public static void main(String[] args) throws IOException {
        ServerSocketChannel socketChannel = ServerSocketChannel.open();

    }
}

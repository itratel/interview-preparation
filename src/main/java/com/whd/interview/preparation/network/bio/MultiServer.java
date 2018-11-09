package com.whd.interview.preparation.network.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author whd.java@gmail.com
 * @date 2018/11/9 10:10
 * @apiNote MultiServer
 */
public class MultiServer {

    static final int PORT = 9999;

    static int clientNo = 1;

    public static void main(String[] args) {
        try {
            newMultiServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void newMultiServer() throws IOException {
        //创建socket服务器
        ServerSocket serverSocket = new ServerSocket(PORT);
        //创建线程池
        ExecutorService executorService = Executors.newCachedThreadPool();

        try {
            while (true){
                Socket socket = serverSocket.accept();
                executorService.execute(new SingleWorker(socket, clientNo));
                clientNo++;
            }
        }finally {
            serverSocket.close();
        }

    }
}

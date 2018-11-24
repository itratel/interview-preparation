package com.whd.interview.preparation.network.bio.simple;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author whd.java@gmail.com
 * @date 2018/11/23 23:43
 * @apiNote Describe the function of this class in one sentence
 **/
public class BIOServer {

    public static void main(String[] args) throws IOException {
        //创建一个有三个线程的线程池
        ExecutorService service = Executors.newFixedThreadPool(3);
        //实例化处理请求业务逻辑的处理器
        RequestHandler requestHandler = new RequestHandler();
        try(ServerSocket serverSocket = new ServerSocket(8888)){
            System.out.println("BIOServer has started, listening on port: " + serverSocket.getLocalSocketAddress());
            //允许多个客户端的连接
            while (true){
                Socket socket = serverSocket.accept();
                System.out.println("Connection from " + socket.getRemoteSocketAddress());
                //为了给线程池可以去进行任务的执行
                service.submit(new ClientHandler(socket, requestHandler));
            }
        }


    }
}

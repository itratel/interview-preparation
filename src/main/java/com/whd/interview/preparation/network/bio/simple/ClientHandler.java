package com.whd.interview.preparation.network.bio.simple;

import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * @author whd.java@gmail.com
 * @date 2018/11/24 1:47
 * @apiNote Describe the function of this class in one sentence
 **/
public class ClientHandler implements Runnable {

    private final Socket socket;

    private final RequestHandler requestHandler;

    public ClientHandler(Socket socket, RequestHandler requestHandler) {
        this.socket = socket;
        this.requestHandler = requestHandler;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public void run() {
        try(Scanner input = new Scanner(socket.getInputStream())){
            //针对每个连接能进行无限次数的交互操作
            while (true){
                //bio真正阻塞的地方是在 io读写操作 的地方，线程拿到连接后一直在等待用户下一步的输入信息，此时线程被耽搁的点
                String request = input.nextLine();
                if ("quit".equals(request)){
                    System.out.println("connection disconnected");
                    break;
                }
                System.out.println("request = " + request);
                //这是业务代码
                String response = requestHandler.handler(request);
                socket.getOutputStream().write(response.getBytes(Charset.defaultCharset()));
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}

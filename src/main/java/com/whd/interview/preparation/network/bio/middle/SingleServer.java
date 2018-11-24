package com.whd.interview.preparation.network.bio.middle;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author whd.java@gmail.com
 * @date 2018/11/8 21:05
 * @apiNote Describe the function of this class in one sentence
 **/
public class SingleServer {

    static final String SERVER_HOST = "127.0.0.1";

    static final int SERVER_PORT = 9999;

    public static void main(String[] args) {
        try {
            newServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void newServer() throws IOException {
        //创建一个新的ServerSocket,用来监听指定端口(SERVER_PORT)的连接请求
        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
        System.out.println("BIOServer has started, listening on port" + serverSocket.getLocalSocketAddress());
        //accept()方法的调用将被阻塞，直到一个连接建立
        Socket socket = serverSocket.accept();
        //获取socket连接的输入流
        InputStream inputStream = socket.getInputStream();

        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(inputStream));

        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

        int content, response;
        while ((content = dataInputStream.readInt()) != 0){
            System.out.println("服务器端收到的边长数据为：" + content);
            response = getResponse(content);
            dataOutputStream.writeInt(response);
            dataOutputStream.flush();
        }
        socket.close();
        serverSocket.close();
    }


    /***
     * 获得响应内容
     * @return String
     */
    private static int getResponse(int content){
        return content * content;
    }


}

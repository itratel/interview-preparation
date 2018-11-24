package com.whd.interview.preparation.network.bio.middle;

import java.io.*;
import java.net.Socket;

/**
 * @author whd.java@gmail.com
 * @date 2018/11/9 10:34
 * @apiNote Describe the function of this class in one sentence
 */
public class SingleWorker implements Runnable {

    private Socket socket;

    private int clientNo;

    SingleWorker(Socket socket, int clientNo) {
        this.socket = socket;
        this.clientNo = clientNo;
    }

    @Override
    public void run() {
        try {
            DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            int length;
            while ((length = dataInputStream.readInt()) != 0){
                System.out.println("从客户端" + clientNo + "接收到的边长数据为：" + length);
                int result = length * length;
                dataOutputStream.writeInt(result);
                dataOutputStream.flush();
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            System.out.println("与客户端" + clientNo + "通信结束");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

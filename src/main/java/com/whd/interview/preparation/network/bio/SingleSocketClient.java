package com.whd.interview.preparation.network.bio;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author whd.java@gmail.com
 * @date 2018/11/8 22:09
 * @apiNote Describe the function of this class in one sentence
 **/
public class SingleSocketClient {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket(SingleServer.SERVER_HOST, SingleServer.SERVER_PORT);
        //获取socket连接的输入流
        InputStream inputStream = socket.getInputStream();

        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(inputStream));

        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

        Scanner sc = new Scanner(System.in);

        boolean flag = false;

        while (!flag){
            System.out.println("请输入正方形的边长: ");
            Integer length = sc.nextInt();
            dataOutputStream.writeInt(length);
            dataOutputStream.flush();

            int response = dataInputStream.readInt();
            System.out.println("服务器返回的计算面积为:" + response);

            while (true) {
                System.out.println("继续计算？(Y/N)");
                String str = sc.next();
                if ("N".equalsIgnoreCase(str)) {
                    dataOutputStream.writeInt(0);
                    dataOutputStream.flush();
                    flag = true;
                    break;
                } else if ("Y".equalsIgnoreCase(str)) {
                    dataOutputStream.write(5);
                    dataOutputStream.flush();
                    break;
                }
            }
        }
        socket.close();
    }
}

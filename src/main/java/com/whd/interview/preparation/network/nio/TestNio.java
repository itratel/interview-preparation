package com.whd.interview.preparation.network.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author whd.java@gmail.com
 * @date 2018/11/8 21:02
 * @apiNote Describe the function of this class in one sentence
 **/
public class TestNio {


    public static void main(String[] args) throws Exception {

        RandomAccessFile aFile = new RandomAccessFile("img/item.txt", "rw");
        FileChannel inChannel = aFile.getChannel();
        //create buffer with capacity of 48 bytes
        ByteBuffer buf = ByteBuffer.allocate(1024);
        //read into buffer.
        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {
            buf.flip();  //make buffer ready for read
            while(buf.hasRemaining()){
                // read 1 byte at a time
                System.out.print((char) buf.get());
            }
            buf.clear(); //make buffer ready for writing
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }


}

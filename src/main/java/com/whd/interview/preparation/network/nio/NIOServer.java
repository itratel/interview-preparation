package com.whd.interview.preparation.network.nio;

import com.whd.interview.preparation.network.bio.simple.RequestHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @author whd.java@gmail.com
 * @date 2018/11/24 2:30
 * @apiNote Describe the function of this class in one sentence
 **/
public class NIOServer {

    private static final int PORT = 9999;

    /***
     * 只有一个主线程 Channel(server, client) Selector Buffer
     * @param args
     */
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //将服务端设置为非阻塞的
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(PORT));
        System.out.println("NIOServer has started, listening on port: " + serverSocketChannel.getLocalAddress());
        //开启一个selector
        Selector selector = Selector.open();
        //将serverSocketChannel注册到selector上面
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //声明一个存储数据的byte buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //实例化逻辑处理器
        RequestHandler handler = new RequestHandler();
        while(true){
            //selector轮询是否有新的请求进来,唯一一个会大声阻塞的地方，只是拿去连接会阻塞，读取数据不会阻塞
            int select = selector.select();
            if (select == 0){
                continue;
            }
            //获取所有的selectKey
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            //通过迭代器实现迭代
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()){
                //获得连接
                SelectionKey key = keyIterator.next();
                if (key.isAcceptable()){
                    ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
                    SocketChannel clientChannel = serverChannel.accept();
                    System.out.println("Connection from " + clientChannel.getRemoteAddress());
                    clientChannel.configureBlocking(false);
                    clientChannel.register(selector, SelectionKey.OP_READ);
                }
                if (key.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    //客户端连接将数据读到buffer中
                    socketChannel.read(buffer);
                    String request = new String(buffer.array()).trim();
                    //清空buffer。方便下一个请求读写数据
                    buffer.clear();
                    //输出客户端连接的地址和请求内容
                    System.out.println(String.format("From %s : %s", socketChannel.getRemoteAddress(), request));
                    //经历业务端的业务逻辑处理
                    String response = handler.handler(request);
                    socketChannel.write(ByteBuffer.wrap(response.getBytes(Charset.defaultCharset())));
                }
                //处理完这个请求后，就要移除掉这个key
                keyIterator.remove();
            }
        }
    }
}

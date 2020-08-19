package com.whd.interview.preparation.network.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Date;

/**
 * <p>MyNettyClient<p>
 *
 * @author whd.java@gmail.com
 * @date 2020/08/19 21:52
 * @apiNote Describe the function of this class in one sentence
 * @since
 */
public class MyNettyClient {


    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup(1);
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {

                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                });
        Channel channel = bootstrap.connect("127.0.0.1", 8888).channel();
        while (true) {
            channel.writeAndFlush(new Date() + ": hello world");
            Thread.sleep(3000);
        }


    }

}

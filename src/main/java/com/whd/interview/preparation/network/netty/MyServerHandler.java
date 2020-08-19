package com.whd.interview.preparation.network.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author whd.java@gmail.com
 * @date 2018/11/25 15:56
 * @apiNote Describe the function of this class in one sentence
 **/
@Slf4j
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * Sub-classes may override this method to change behavior.
     * @param ctx
     * @param msg
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //msg 读取客户端操作
        log.info("服务端收到的消息为：" + msg);
        System.out.println("服务端收到的消息为：" + msg);
        //ctx 写数据给客户端
        super.channelRead(ctx, msg);
    }
}

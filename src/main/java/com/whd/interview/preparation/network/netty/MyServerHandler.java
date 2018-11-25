package com.whd.interview.preparation.network.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author whd.java@gmail.com
 * @date 2018/11/25 15:56
 * @apiNote Describe the function of this class in one sentence
 **/
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * Sub-classes may override this method to change behavior.
     * @param ctx
     * @param msg
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        //msg 读取客户端操作
        //ctx 写数据给客户端
    }
}

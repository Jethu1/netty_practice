package com.phei.netty.mine;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

/**
 * Created by jet on 2017/6/1.
 */
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> { //1
    private final ChannelGroup group;

    public TextWebSocketFrameHandler(ChannelGroup group) {
        this.group = group;
    }


    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {    //2
        if (evt == WebSocketServerProtocolHandler.ServerHandshakeStateEvent.HANDSHAKE_COMPLETE) {

            ctx.pipeline().remove(HttpRequestHandler.class);    //3

            group.writeAndFlush(new TextWebSocketFrame("Client " + ctx.channel() + " joined"));//4

            group.add(ctx.channel());    //5
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }


    public void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        group.writeAndFlush(msg.retain());    //6
    }
}

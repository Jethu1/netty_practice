/*
 * Copyright 2013-2018 Lilinfeng.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.phei.netty.protocol.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * @author lilinfeng
 * @date 2014年2月14日
 * @version 1.0
 */
public class ChineseProverbServer {
    public void run(int port) throws Exception {
	EventLoopGroup group = new NioEventLoopGroup(); // 接受连接事件组，处理每个连接业务事件组
	try {
	    Bootstrap b = new Bootstrap();//设置引导类
		System.out.println("运行");
		b.group(group);//添加groop
		b.channel(NioDatagramChannel.class);//设置通道类型，udp相关的
		b.option(ChannelOption.SO_BROADCAST, true);//设置为UDP广播
		b.localAddress(port);//启动服务器绑定的端口
		b.handler(new ChineseProverbServerHandler());//实现ChannelInitializer接口的initChannel方法，将处理器（业务逻辑）加到通道管道的末尾：

	    b.bind(port).sync().channel().closeFuture().await();
		System.out.println("运行2");
	} finally {
	    group.shutdownGracefully();
	}
    }

    public static void main(String[] args) throws Exception {
	int port = 8080;
	if (args.length > 0) {
	    try {
		port = Integer.parseInt(args[0]);
	    } catch (NumberFormatException e) {
		e.printStackTrace();
	    }
	}
	ChineseProverbServer cps=
	new ChineseProverbServer();
		cps.run(port);
//		new ChineseProverbServerHandler().messageReceived(ChannelHandlerContext ctx, DatagramPacket msg);
    }
}

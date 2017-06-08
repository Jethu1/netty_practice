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
package com.phei.netty.basic;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import practice.EchoServer;
import practice.EchoServerHandler;

/**
 * @author lilinfeng
 * @date 2014年2月14日
 * @version 1.0
 */
public class TimeServer {

    public void bind(int port) throws Exception {
	// 配置服务端的NIO线程组
	EventLoopGroup bossGroup = new NioEventLoopGroup();
	EventLoopGroup workerGroup = new NioEventLoopGroup();
	try {
	    ServerBootstrap b = new ServerBootstrap();
	    b.group(bossGroup, workerGroup)
		    .channel(NioServerSocketChannel.class)
		    .localAddress(port)
		    .childHandler(new ChannelInitializer<SocketChannel>() { // 绑定客户端连接时候触发操作

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					System.out.println("connected...; Client:" + ch.remoteAddress());
					ch.pipeline().addLast(new TimeServerHandler()); // 客户端触发操作
				}
			});
	    // 绑定端口，同步等待成功
	    ChannelFuture f = b.bind(port).sync();
		System.out.println(TimeServer.class + " started and listen on " + f.channel().localAddress());
	    // 等待服务端监听端口关闭
	    f.channel().closeFuture().sync();
	} finally {
	    // 优雅退出，释放线程池资源
	    bossGroup.shutdownGracefully();
	    workerGroup.shutdownGracefully();
	}
    }



    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
	int port = 8080;
	if (args != null && args.length > 0) {
	    try {
		port = Integer.valueOf(args[0]);
	    } catch (NumberFormatException e) {
		// 采用默认值
	    }
	}
	new TimeServer().bind(port);
    }
}

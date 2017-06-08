package practice1_EchoDetailName;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * Created by jet on 2017/6/2.
 */
public class EchoServer {

    private final int port; // 服务端绑定端口

    public EchoServer(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // 接受连接事件组
        EventLoopGroup workerGroup = new NioEventLoopGroup(); // 每个连接业务处理事件组

        try {
            ServerBootstrap b = new ServerBootstrap(); // 引导器
            // 指定事件组，通道、绑定地址、业务处理器
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port)).childHandler(new ChannelInitializer<SocketChannel>() { // 虽然EchoServerHandler和ChannelInitializer都是ChannelHandler的实现类，但这里不能直接传入EchoServerHandler，否则会导致业务处理器无法使用

                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    // 将业务处理器加到通道管理线（处理器队列）的末尾
                    ch.pipeline().addLast(new EchoServerHandler());
                }
            });

            ChannelFuture f = b.bind().sync(); // 绑定指定端口
            System.out.println(EchoServer.class.getName() + " started and listen on " + f.channel().localAddress());
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully().sync(); // 释放资源和线程池
        }
    }

    public static void main(String[] args) throws Exception {
        args = new String[1];
        args[0] = "8180";

        if (args.length != 1) {
            System.err.println("?Usage: ?" + EchoServer.class.getSimpleName() + "<port>?");
        }

        int port = Integer.parseInt(args[0]);
        new EchoServer(port).start();
    }
}
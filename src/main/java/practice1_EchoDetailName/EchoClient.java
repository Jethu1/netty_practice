package practice1_EchoDetailName;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * Created by jet on 2017/6/2.
 */

public class EchoClient {
    private final String host; // 服务器地址
    private final int port; // 服务器端口

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap(); // 客户端引导器

            // 指定事件组、客户端通道、远程服务端地址、业务处理器
            b.group(group).channel(NioSocketChannel.class).remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new EchoClientHandler());
                        }
                    });

            // 连接到服务端，sync()阻塞直到连接过程结束
            ChannelFuture f = b.connect().sync();
            f.channel().writeAndFlush("hello server, can you see me now？");
            // 等待通道关闭
            f.channel().closeFuture().sync();
        } finally {
            // 关闭引导器并释放资源，包括线程池
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        args = new String[2];
        args[0] = "127.0.0.1";
        args[1] = "8180";

        if (args.length != 2) {
            System.err.println("Usage: " + EchoClient.class.getSimpleName() + " <host> <port>");
            return;
        }

        final String host = args[0];
        final int port = Integer.parseInt(args[1]);
        new EchoClient(host, port).start();
    }
}
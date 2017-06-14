package practice12_Udp_Multicast;

/**
 * Created by jet on 2017/6/14.
 */
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ChannelFactory;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.InternetProtocolFamily;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.NetUtil;

import java.net.*;
import java.util.Enumeration;

public class MulticastServer extends Thread {
    private InetSocketAddress groupAddress;

    public MulticastServer(InetSocketAddress groupAddress) {
        this.groupAddress = groupAddress;
    }

    public void run() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
//            NetworkInterface ni = NetworkInterface.getByName("en1");
            NetworkInterface ni = NetUtil.LOOPBACK_IF;
            Enumeration<InetAddress> addresses = ni.getInetAddresses();
            InetAddress localAddress = null;
            while (addresses.hasMoreElements()) {
                InetAddress address = addresses.nextElement();
                if (address instanceof Inet4Address){
                    localAddress = address;
                }
            }

            Bootstrap b = new Bootstrap();
                    b.group(group)
                    .channelFactory(new ChannelFactory<NioDatagramChannel>() {

                        public NioDatagramChannel newChannel() {
                            return new NioDatagramChannel(InternetProtocolFamily.IPv4);
                        }
                    })
                    .localAddress(localAddress, groupAddress.getPort())
                    .option(ChannelOption.IP_MULTICAST_IF, ni)
                    .option(ChannelOption.SO_REUSEADDR, true)
                    .handler(new ChannelInitializer<NioDatagramChannel>() {
                        @Override
                        public void initChannel(NioDatagramChannel ch) throws Exception {
                            ch.pipeline().addLast(new ServerMulticastHandler());
                        }
                    });

            NioDatagramChannel ch = (NioDatagramChannel)b.bind(groupAddress.getPort()).sync().channel();
            ch.joinGroup(groupAddress, ni).sync();
            System.out.println("server");

            ch.closeFuture().await();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        InetSocketAddress groupAddress = new InetSocketAddress("239.255.27.1", 1234);
        new MulticastServer(groupAddress).run();
    }
}

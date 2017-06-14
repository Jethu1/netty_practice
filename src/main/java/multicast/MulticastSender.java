package udp.multicast;

/**
 * Created by jet on 2017/5/27.
 */
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

//客户端
public class MulticastSender
{
    public static void main(String[] args) throws IOException
    {
        int port = 8888;
        byte[] msg = "Connection successfully!!!".getBytes();

        InetAddress inetRemoteAddr = InetAddress.getByName("224.0.0.5");

    /*
     * Java UDP组播应用程序主要通过MulticastSocket实例进行通信,它是DatagramSocket的是一个子类,
     * 其中包含了一些额外的可以控制多播的属性.
     *
     * 注意：
     *
     * 多播数据报包实际上可以通过DatagramSocket发送,只需要简单地指定一个多播地址。
     * 我们这里使用MulticastSocket,是因为它具有DatagramSocket没有的能力
     */
        MulticastSocket client = new MulticastSocket();

        DatagramPacket sendPack = new DatagramPacket(msg, msg.length,
                inetRemoteAddr, port);

        client.send(sendPack);

        System.out.println("Client send msg complete");

        client.close();

    }

}

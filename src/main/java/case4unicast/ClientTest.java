package udp.case4unicast;

/**
 * Created by jet on 2017/5/27.
 */
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

// 客户端
public class ClientTest
{
    private static final int MAXRECEIVED = 255;

    public static void main(String[] args) throws IOException
    {
        byte[] msg = new String("connect test successfully!!!").getBytes();

        DatagramSocket client = new DatagramSocket();

        InetAddress inetAddr = InetAddress.getLocalHost();
        SocketAddress socketAddr = new InetSocketAddress(inetAddr, 8888);

        DatagramPacket sendPacket = new DatagramPacket(msg, msg.length,socketAddr);

        client.send(sendPacket);

        client.close();
    }
}

package udp.multicast;

/**
 * Created by jet on 2017/5/27.
 */
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Arrays;

//服务端
public class MulticastReceive
{
    public static void main(String[] args) throws IOException
    {
        InetAddress inetRemoteAddr = InetAddress.getByName("224.0.0.5");

        DatagramPacket recvPack = new DatagramPacket(new byte[1024], 1024);

        MulticastSocket server = new MulticastSocket(8888);

    /*
     * 如果是发送数据报包,可以不加入多播组; 如果是接收数据报包,必须加入多播组; 这里是接收数据报包,所以必须加入多播组;
     */
        server.joinGroup(inetRemoteAddr);

        System.out.println("---------------------------------");
        System.out.println("Server current start......");
        System.out.println("---------------------------------");

        while (true)
        {
            server.receive(recvPack);

            byte[] recvByte = Arrays.copyOfRange(recvPack.getData(), 0, recvPack.getLength());

            System.out.println("Server receive msg:" + new String(recvByte));
        }

    }

}
package udp.case4unicast;

/**
 * Created by jet on 2017/5/27.
 */
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

//服务端
public class ServerTest
{
    private static final int MAXREV = 255;

    public static void main(String[] args) throws IOException
    {
        DatagramSocket server = new DatagramSocket(8888);
        DatagramPacket recvPacket = new DatagramPacket(new byte[MAXREV], MAXREV);

        while (true)
        {
            server.receive(recvPacket);

            byte[] receiveMsg = Arrays.copyOfRange(recvPacket.getData(),
                    recvPacket.getOffset(),
                    recvPacket.getOffset() + recvPacket.getLength());

            System.out.println("Handing at client "
                    + recvPacket.getAddress().getHostName() + " ip "
                    + recvPacket.getAddress().getHostAddress());

            System.out.println("Server Receive Data:" + new String(receiveMsg));

            server.send(recvPacket);

        }

    }

}
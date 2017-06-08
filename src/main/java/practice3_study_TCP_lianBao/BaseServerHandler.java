package practice3_study_TCP_lianBao;

/**
 * Created by jet on 2017/6/6.
 */
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class BaseServerHandler extends ChannelInboundHandlerAdapter{


    private int counter;


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        String body = (String)msg;
        System.out.println("server receive order : " + body + ";the counter is: " + ++counter);
        ctx.writeAndFlush("I am the server!");
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}

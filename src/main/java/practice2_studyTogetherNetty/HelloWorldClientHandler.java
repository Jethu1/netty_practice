package practice2_studyTogetherNetty;

/**
 * Created by jet on 2017/6/5.
 */
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class HelloWorldClientHandler extends ChannelInboundHandlerAdapter{


    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("HelloWorldClientHandler Active");
        ctx.fireChannelActive();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("HelloWorldClientHandler read Message:"+msg);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
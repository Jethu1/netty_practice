package practice4_LengthFieldBasedFrameDecoder;

/**
 * Created by jet on 2017/6/6.
 */
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class CustomServerHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof CustomMsg) {
            CustomMsg customMsg = (CustomMsg)msg;
            System.out.println("Client->Server:"+ctx.channel().remoteAddress()+" send "+customMsg.getBody());
        }

    }

}

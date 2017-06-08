package practice1_EchoDetailName;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by jet on 2017/6/2.
 */
//@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;

        System.out.println("Server received: " + ByteBufUtil.hexDump(buf.readBytes(buf.readableBytes()))); //缓冲内部存储读写位置，readBytes将指针后移
        // System.out.println("?Server received: ?" + msg);
        buf.resetReaderIndex(); // 重置读写位置，如果省略这一句，ctx.write(msg)往客户端发送的数据为空

        ctx.write(msg);
        System.out.println(ctx.read().toString());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 读写完毕，调用flush将数据真正发送到客户端
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace(); // 打印异常
        ctx.close(); // 关闭通道
    }
}

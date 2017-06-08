package practice;

import io.netty.channel.ChannelHandlerContext;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
  import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
/**
 * Created by jet on 2017/6/2.
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
         System.out.println("server channelRead...; received:" + msg.toString()+ctx.toString());
         ctx.write(msg);

    }


    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
         System.out.println("server channelReadComplete..");
                 // 第一种方法：写一个空的buf，并刷新写出区域。完成后关闭sock channel连接。
         ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
                //ctx.flush(); // 第二种方法：在client端关闭channel连接，这样的话，会触发两次channelReadComplete方法。
                 //ctx.flush().close().sync(); // 第三种：改成这种写法也可以，但是这中写法，没有第一种方法的好。

    }



    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
         System.out.println("server occur exception:" + cause.getMessage());
         cause.printStackTrace();
         ctx.close(); // 关闭发生异常的连接

    }
}


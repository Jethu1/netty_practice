package practice5_Protobuf;

/**
 * Created by jet on 2017/6/6.
 */
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

import practice5_Protobuf.RichManProto.RichMan.Car;
import practice5_Protobuf.RichManProto.RichMan.CarType;

public class ProtoBufClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("=======================================");
        RichManProto.RichMan.Builder builder = RichManProto.RichMan.newBuilder();
        builder.setName("王思聪");
        builder.setId(1);
        builder.setEmail("wsc@163.com");

         builder.addCarsBuilder().setName("上海大众超跑").setType(CarType.DASAUTO);

//        List<RichManProto.RichMan.Car> cars = new ArrayList<RichManProto.RichMan.Car>();
//        Car car1 = ;
//        Car car2 = RichManProto.RichMan.Car.newBuilder().setName("Aventador").setType(CarType.LAMBORGHINI).build();
//        Car car3 = RichManProto.RichMan.Car.newBuilder().setName("奔驰SLS级AMG").setType(CarType.BENZ).build();

//        cars.add(car1);
//        cars.add(car2);
//        cars.add(car3);

        ctx.writeAndFlush(builder.build());
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
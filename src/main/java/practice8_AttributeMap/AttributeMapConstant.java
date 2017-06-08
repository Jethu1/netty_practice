package practice8_AttributeMap;

/**
 * Created by jet on 2017/6/8.
 */

import io.netty.util.AttributeKey;

public class AttributeMapConstant {

    public static final AttributeKey<NettyChannel> NETTY_CHANNEL_KEY = AttributeKey.valueOf("netty.channel");

}
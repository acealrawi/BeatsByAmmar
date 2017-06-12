package beatsServer;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import protoManagers.MessageManager;

/**
 * Created by Gebruiker on 5/22/2017.
 */
public class TestServerStringHandler extends SimpleChannelInboundHandler<String> {
    Channel clientChannel;
    TestServerStringHandler(Channel channel) {
        clientChannel = channel;
    }

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {



            System.out.println(s);

//            if (clientChannel!=null){
//                clientChannel.writeAndFlush(MessageManager.createSensorMessage(Integer.parseInt(s)));
//            }
//            else{
//                System.out.println("nah null");
//            }



    }
}

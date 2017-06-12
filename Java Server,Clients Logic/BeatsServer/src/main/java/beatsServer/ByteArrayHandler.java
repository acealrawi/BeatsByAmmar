package beatsServer;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import proto.ProtoMessageOuterClass.ProtoMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import proto.ProtoMessageOuterClass;
import protoManagers.MessageManager;

/**
 * Created by Gebruiker on 6/11/2017.
 */
public class ByteArrayHandler extends SimpleChannelInboundHandler<byte[]> {
    public static ChannelGroup channels= new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, byte[] bytes) throws Exception {
        System.out.println(bytes.length);

//        if (ProtoMessage.parseFrom(bytes))

        System.out.println(bytes[0]);
        if (bytes[0]==10){
            System.out.println("requested: "+ProtoMessageOuterClass.ProtoMessage.parseFrom(bytes).getPatientRequest().getPatientNumber());
            MessageManager.requestProcessingModule(channelHandlerContext,ProtoMessageOuterClass.ProtoMessage.parseFrom(bytes));

            if (!channels.contains(channelHandlerContext.channel())){
                channels.add(channelHandlerContext.channel());
                System.out.println("channel added");
            }
        }
        else {
            System.out.println("received: "+new String(bytes));
            for (Channel channel: channels){
                channel.writeAndFlush(MessageManager.createSensorMessage(Integer.parseInt(new String(bytes))));
                System.out.println("sending the  hart");
            }
        }
    }

    public void exceptionCaught(ChannelHandlerContext channelHandlerContext,Throwable cause){
        System.out.println("cought something bad: "+cause.getMessage());

    }
}

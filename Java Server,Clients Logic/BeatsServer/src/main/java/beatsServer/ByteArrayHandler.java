package beatsServer;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import proto.ProtoMessageOuterClass;
import protoManagers.MessageManager;

/**
 * Created by Gebruiker on 6/11/2017.
 */

/***
 * This class handles incoming byte array data and outgoing protobuf messages
 *
 * Uhen a message come it goes directly to the byte array in the channelRead0 function.
 * Using the first byte of the byte array, it is determined wither its protobuf byte array or something else(in this case its always string).
 *
 */
public class ByteArrayHandler extends SimpleChannelInboundHandler<byte[]> {
    public static ChannelGroup channels= new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, byte[] bytes) throws Exception {
//        System.out.println(bytes.length);

//        if (ProtoMessage.parseFrom(bytes))

//        System.out.println(bytes[0]);
        if (bytes[0]==10){
            System.out.println("requested: "+ProtoMessageOuterClass.ProtoMessage.parseFrom(bytes).getPatientRequest().getPatientNumber());
            MessageManager.requestProcessingModule(channelHandlerContext,ProtoMessageOuterClass.ProtoMessage.parseFrom(bytes));//process the request and also creates a response and send it using the channelHandlerContext.

            //the code below adds the GUI client to a channel group so it can later be used
            if (!channels.contains(channelHandlerContext.channel())){
                channels.add(channelHandlerContext.channel());
                System.out.println("channel added");
            }
        }
        //if the incoming byte array is string
        else {

            System.out.println("received: "+new String(bytes));
            //create a sensor message put the data in it and send it to the gui client.
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

package Client;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import proto.ProtoMessageOuterClass.ProtoMessage;
import protoManagers.MessageManager;


public class ClientProtoHandler extends SimpleChannelInboundHandler<ProtoMessage>{
    Patient patient = new Patient();
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ProtoMessage message) throws Exception {
//        patient.setPatient(MessageManager.responseProcessingModule(message));
//        System.out.println(MessageManager.responseProcessingModule(message));
        AbstractDataHandler.dataReceived(MessageManager.responseProcessingModule(message));

    }

    public void exceptionCaught(ChannelHandlerContext channelHandlerContext,Throwable cause){
        System.out.println("cought something bad: "+cause.getMessage());

    }
}

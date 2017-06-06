package beatsServer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import proto.ProtoMessageOuterClass;
import protoManagers.MessageManager;

/**
 * Created by Gebruiker on 5/17/2017.
 */
public class TestServerProtoHandler extends SimpleChannelInboundHandler<ProtoMessageOuterClass.ProtoMessage>{
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ProtoMessageOuterClass.ProtoMessage message) throws Exception {
        System.out.println("requested: "+message.getPatientRequest(0).getPatientNumber());
        MessageManager.requestProcessingModule(channelHandlerContext,message);
    }
}

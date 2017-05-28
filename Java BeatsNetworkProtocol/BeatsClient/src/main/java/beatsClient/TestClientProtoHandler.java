
package beatsClient;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import protoManagers.MessageManager;
import proto.ProtoMessageOuterClass.*;




/**
 * Created by Gebruiker on 5/18/2017.
 */
public class TestClientProtoHandler extends SimpleChannelInboundHandler<ProtoMessage> {



    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }


    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ProtoMessage message) throws Exception {
        System.out.println(MessageManager.responseProcessingModule(message));
        System.out.println("Give me the MENTALY ILL NUMBER: ");
//        ProtoPatient patient = MessageManager.responseProcessingModule(message);
//        Patient.patientNumber = patient.getPatientNumber();
//        Patient.name = patient.getName();
//        Patient.age = patient.getAge();

    }

    public void exceptionCaught(ChannelHandlerContext channelHandlerContext,Throwable cause){
        System.out.println("cought something bad: "+cause.getMessage());
    }


}

package Client;


import com.google.protobuf.Descriptors;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import proto.ProtoMessageOuterClass;
import proto.ProtoMessageOuterClass.ProtoMessage;
import protoManagers.MessageManager;


public class ClientProtoHandler extends SimpleChannelInboundHandler<ProtoMessage>{
    Patient patient = new Patient();
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ProtoMessage message) throws Exception {
//        patient.setPatient(MessageManager.responseProcessingModule(message));
        switch (message.getMessageCase()){
            case PATIENTRESPONSE:
                System.out.println(MessageManager.responseProcessingModule(message));
                AbstractDataHandler.dataReceived(MessageManager.responseProcessingModule(message));
                break;
            case SENSOR:
                System.out.println(MessageManager.sensorProcessingModule(message));
                AbstractDataHandler.sensorDataReceived(MessageManager.sensorProcessingModule(message));
                break;
        }
//        System.out.println(MessageManager.responseProcessingModule(message));
//        AbstractDataHandler.dataReceived(MessageManager.responseProcessingModule(message));



    }

    public void exceptionCaught(ChannelHandlerContext channelHandlerContext,Throwable cause){
        System.out.println("cought something bad: "+cause.getMessage());
//        cause.printStackTrace();
    }
}

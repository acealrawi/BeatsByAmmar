/**
 * Created by Gebruiker on 5/17/2017.
 */
package protoManagers;
import io.netty.channel.ChannelHandlerContext;
import proto.ProtoPatientOuterClass.*;
import proto.ProtoMessageOuterClass.*;
public class MessageManager {


    public static ProtoMessage createResponseMessage(ProtoPatient patient){
        PatientResponse.Builder response = PatientResponse.newBuilder();
        response.setPatient(patient);
        ProtoMessage.Builder message = ProtoMessage.newBuilder();
        message.setType(ProtoMessage.Type.response);
        message.addPatientResponse(response.build());
        return message.build();
    }

    public static ProtoMessage createRequestMessage(int patientNumber){
        PatientRequest.Builder request = PatientRequest.newBuilder();
        request.setPatientNumber(patientNumber);
        ProtoMessage.Builder message = ProtoMessage.newBuilder();
        message.setType(ProtoMessage.Type.request);
        message.addPatientRequest(request.build());
        return message.build();
    }

    //This method process incoming request from the client.
    public static void requestProcessingModule(ChannelHandlerContext ctx, ProtoMessage message){
        //extract the patientNumber from the request
        int patientNumber = message.getPatientRequest(0).getPatientNumber();
        for (ProtoPatient patient : PatientManager.getPatients()){
            if (patientNumber == patient.getPatientNumber()){
//                only test
//                System.out.println(patient.getName());
//                System.out.println(responseProcessingModule(createResponseMessage(patient)));
                ctx.writeAndFlush(createResponseMessage(patient));
            }

        }
    }
    public static ProtoPatient responseProcessingModule(ProtoMessage message){
        return message.getPatientResponse(0).getPatient();

    }
}

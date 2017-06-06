package Client;

import proto.ProtoPatientOuterClass;

/**
 * Created by Gebruiker on 5/25/2017.
 */
public class Patient {

    private static ProtoPatientOuterClass.ProtoPatient savedPatient ;

    public static void setPatient(ProtoPatientOuterClass.ProtoPatient patient){
        savedPatient = patient;
    }
    public static ProtoPatientOuterClass.ProtoPatient getPatient(){
        return savedPatient;
    }


}

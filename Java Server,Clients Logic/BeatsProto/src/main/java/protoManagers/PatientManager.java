/**
 * Created by Gebruiker on 5/17/2017.
 */
package protoManagers;
import proto.ProtoPatientOuterClass.*;

import java.util.ArrayList;

public class PatientManager {

    private static ArrayList<ProtoPatient> patients = new ArrayList<ProtoPatient>();
    public static void addPatient(int patientNumber, String name, int age,String gender, int weight, String sport){
        ProtoPatient.Builder patient = ProtoPatient.newBuilder();
        patient.setPatientNumber(patientNumber);
        patient.setName(name);
        patient.setAge(age);
        patient.setGender(gender);
        patient.setWeight(weight);
        patient.setSport(sport);
        patients.add(patient.build());
    }

    public static ArrayList<ProtoPatient> getPatients(){
        return patients;
    }


}

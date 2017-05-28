package Client;

import proto.ProtoMessageOuterClass;
import proto.ProtoPatientOuterClass;

import java.util.ArrayList;

/**
 * Created by Gebruiker on 5/25/2017.
 */




public abstract class AbstractDataHandler {

    private static ArrayList<newDataListener> listeners = new ArrayList<newDataListener>();

    public static void addListener(newDataListener data) {
        listeners.add(data);
    }

    public static void dataReceived(ProtoPatientOuterClass.ProtoPatient patient) {
        for (newDataListener rl : listeners)
            rl.newData(patient);
    }




    public interface newDataListener{
        void newData(ProtoPatientOuterClass.ProtoPatient patient);
    }
}

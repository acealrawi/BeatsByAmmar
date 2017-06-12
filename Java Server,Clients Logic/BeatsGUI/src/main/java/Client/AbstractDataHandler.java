package Client;

import proto.ProtoMessageOuterClass;
import proto.ProtoPatientOuterClass;

import java.util.ArrayList;

/**
 * Created by Gebruiker on 5/25/2017.
 */

/***
 * In this class two listeners are implemented
 * The newDataListeners listen to Patient Proto Message
 * The newSensorDataListener listen to Sensor Proto Message
 */


public abstract class AbstractDataHandler {

    private static ArrayList<newDataListener> listeners = new ArrayList<newDataListener>();
    private static ArrayList<newSensorDataListener> sensorListeners = new ArrayList<newSensorDataListener>();
    public static void addListener(newDataListener data) {
        listeners.add(data);
    }

    public static void addSensorListener(newSensorDataListener sensorData) {
        sensorListeners.add(sensorData);
    }

    public static void dataReceived(ProtoPatientOuterClass.ProtoPatient patient) {
        for (newDataListener rl : listeners)
            rl.newData(patient);
    }
    public static void sensorDataReceived(ProtoMessageOuterClass.Sensor sensor){
        for (newSensorDataListener rl: sensorListeners){
            rl.newSensorData(sensor);
        }
    }




    public interface newDataListener{
        void newData(ProtoPatientOuterClass.ProtoPatient patient);
    }
    public interface newSensorDataListener{
        void newSensorData(ProtoMessageOuterClass.Sensor sensor);
    }
}

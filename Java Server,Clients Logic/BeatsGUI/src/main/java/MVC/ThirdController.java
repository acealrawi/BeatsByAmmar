package MVC;

import Client.AbstractDataHandler;
import Client.Patient;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import proto.ProtoMessageOuterClass;
import proto.ProtoPatientOuterClass;
import sun.management.Sensor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Gebruiker on 5/25/2017.
 */
public class ThirdController extends Thread implements Initializable {
    View view = new View();

    @FXML
    private Label number;

    @FXML
    private TextField heartRate;

    @FXML
    private Label gender;

    @FXML
    private LineChart<String, Double> heartGraph;

    @FXML
    private VBox background3;

    @FXML
    private TextField minField;

    @FXML
    private Label name;

    @FXML
    private Label weight;

    @FXML
    private TextField maxField;

    @FXML
    private Label age;

    XYChart.Series<String, Double> series = new XYChart.Series<String, Double>();
    int chartIndex = 0;

    @FXML
    void handleMouse() {
        new ThirdController().start();



        heartRate.setText("75");
        maxField.setText("Max: 90");
        minField.setText("Min: 50");


        name.setText("tycho");
        gender.setText("Male");
        age.setText("18");
        number.setText("1234567");
        weight.setText("85");




        for(int index = 0; index < 60; index++){
            if(index < 9){
                series.getData().add(new XYChart.Data(Integer.toString(index), 25));
            }
            if(index == 11){
                series.getData().add(new XYChart.Data(Integer.toString(11), 40));
                series.getData().add(new XYChart.Data(Integer.toString(11), 10));
            }

            if(index > 12 && index < 20){
                series.getData().add(new XYChart.Data(Integer.toString(index), 25));
            }

            if(index == 22){
                series.getData().add(new XYChart.Data(Integer.toString(22), 40));
                series.getData().add(new XYChart.Data(Integer.toString(22), 10));
            }

            if(index > 23){
                series.getData().add(new XYChart.Data(Integer.toString(index), 25));
            }
        }

        //heartGraph.getData().add(series);

    }

    @FXML
    public void testBack(){
        try{
            Parent secondParent = FXMLLoader.load(getClass().getClassLoader().getResource("FxmlScreens/SecondScreen.fxml"));
            Stage secondStage = view.getStage();
            Scene secondScene = new Scene(secondParent, 500, 350);
            secondStage.setScene(secondScene);
            secondStage.setTitle("Saxion hospital");
            secondStage.setResizable(false);
            secondStage.show();

        }catch (IOException io){
            io.printStackTrace();
        }
    }

    public void initialize(URL location, ResourceBundle resources) {
        heartGraph.setLegendVisible(false);

        heartGraph.setCreateSymbols(false);
        updateThirdScreen(Patient.getPatient());
        AbstractDataHandler.addSensorListener(new AbstractDataHandler.newSensorDataListener() {
            public void newSensorData(ProtoMessageOuterClass.Sensor sensor) {
                updateHeartRate(sensor);
            }
        });


    }
    public void updateThirdScreen(final ProtoPatientOuterClass.ProtoPatient patient){
        Platform.runLater(new Runnable() {
            public void run() {
                if (patient != null) {
                    name.setText(patient.getName());
                    age.setText(String.valueOf(patient.getAge()));
                    number.setText(String.valueOf(patient.getPatientNumber()));
                    gender.setText(patient.getGender());
                    weight.setText(String.valueOf(patient.getWeight()));

                }
                else{
                    System.out.println("third screen patient is null");
                }

            }
        });
    }
    public void updateHeartRate(final ProtoMessageOuterClass.Sensor sensor){

            Platform.runLater(new Runnable() {
                public void run() {
                    if (sensor!=null){
                        series.getData().add(new XYChart.Data(Integer.toString(chartIndex), sensor.getValue()));
                        if(chartIndex == 0){
                            heartGraph.getData().add(series);
                        }
                        chartIndex++;

                    }
                    else{
                        System.out.println("heart rate is null");
                    }
                }
            });
            //heartRate.setText(String.valueOf(sensor.getValue()));





    }

}

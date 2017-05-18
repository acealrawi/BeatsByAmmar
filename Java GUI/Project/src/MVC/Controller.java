package MVC;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Controller extends Thread{

    View view = new View();



    @FXML
    Button ContinueButton, SpreadSheetButton;

    @FXML
    TextField NumberInput, heartRate, maxField, minField;

    @FXML
    Label name, gender, age, number, weight;

    @FXML
    LineChart<String, Double> heartGraph;


    public Controller(){

    }

    public void run(){
        SetupTCP(true);
    }

    public void SetupTCP(boolean status){
        String[] recievedData = new String[1000];
        System.out.println("Test");
        try{
            ServerSocket tcpSocket = new ServerSocket(8800);
            Socket accept = tcpSocket.accept();
            String line;
            int index = 0;

            BufferedReader dataIn = new BufferedReader(new InputStreamReader(accept.getInputStream()));
            while((line = dataIn.readLine()) != null){
                recievedData[index] = line;
                index++;
            }
        }catch(IOException ioe){
            ioe.printStackTrace();
        }

        for(int i = 0; i < recievedData.length; i++){
            if(recievedData[i] != null){
                System.out.println(recievedData[i]);
            }

        }
    }

    public void checkInput() {
        NumberInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    NumberInput.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    public void handleContinueButton(){
        try{
            Parent secondParent = FXMLLoader.load(getClass().getClassLoader().getResource("SecondScreen/SecondScreen.fxml"));
            Stage secondStage = view.getStage();

            secondStage.setScene(new Scene(secondParent, 500, 350));
            secondStage.setTitle("Saxion hospital");
            secondStage.setResizable(false);
            secondStage.show();
        }catch (IOException io){
            io.printStackTrace();
        }
    }

    public void handleBackButton(){
        try{
            Parent secondParent = FXMLLoader.load(getClass().getClassLoader().getResource("FirstScreen/FirstScreen.fxml"));
            Stage secondStage = view.getStage();

            secondStage.setScene(new Scene(secondParent, 500, 350));
            secondStage.setTitle("Saxion hospital");
            secondStage.setResizable(false);
            secondStage.show();
        }catch (IOException io){
            io.printStackTrace();
        }
    }

    public void handleSecondContinueButton(){
        try{
            Parent secondParent = FXMLLoader.load(getClass().getClassLoader().getResource("ThirdScreen/ThirdScreen.fxml"));
            Stage secondStage = view.getStage();
            Scene secondScene = new Scene(secondParent, 1200, 900);
            secondStage.setScene(secondScene);
            secondStage.setTitle("Saxion hospital");
            secondStage.setResizable(false);
            secondStage.show();
        }catch (IOException io){
            io.printStackTrace();
        }
    }

    public void handleMouse(){
        new Controller().start();

        heartRate.setText("75");
        maxField.setText("Max: 90");
        minField.setText("Min: 50");

        name.setText("Tycho");
        gender.setText("Male");
        age.setText("19");
        number.setText("424176");
        weight.setText("85");

        XYChart.Series<String, Double> series = new XYChart.Series<String, Double>();

        heartGraph.setLegendVisible(false);

        heartGraph.setCreateSymbols(false);
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
        heartGraph.getData().add(series);
    }
}

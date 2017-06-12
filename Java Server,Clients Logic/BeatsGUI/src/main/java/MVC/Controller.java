package MVC;

import Client.Patient;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.stage.Stage;
import proto.ProtoPatientOuterClass;
import Client.AbstractDataHandler;

import javax.net.ssl.HostnameVerifier;
import java.lang.reflect.GenericArrayType;


public class Controller extends AbstractDataHandler {

    View view = new View();
    int x = 0;

    @FXML
    TextField NumberInput;

    @FXML
    Button settingButton;


    public void changeSetting(){

        if(x==0){
            settingButton.setStyle("-fx-background-image: url('CSS/hover.png')");
            x = 1;
        }else if(x == 1){
            settingButton.setStyle("-fx-background-image: url('CSS/gear.png')");
            x = 0;
        }
    }

    public void handleSetting(){
        try{
            Parent secondParent = FXMLLoader.load(getClass().getClassLoader().getResource("FxmlScreens/SettingsScreen.fxml"));
            Stage secondStage = view.getStage();

            secondStage.setScene(new Scene(secondParent, 360, 180));
            secondStage.setTitle("Saxion hospital");
            secondStage.setResizable(false);
            secondStage.show();
        }catch (Exception io){
            io.printStackTrace();
        }
    }



    public void checkInput() {
        NumberInput.textProperty().addListener(new ChangeListener<String>() {
            //@Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    NumberInput.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }


    public void handleContinueButton(){
        try{
            Parent secondParent = FXMLLoader.load(getClass().getClassLoader().getResource("FxmlScreens/SecondScreen.fxml"));
            Stage secondStage = view.getStage();

            secondStage.setScene(new Scene(secondParent, 500, 350));
            secondStage.setTitle("Saxion hospital");
            secondStage.setResizable(false);
            secondStage.show();
            addListener(new AbstractDataHandler.newDataListener() {
                public void newData(ProtoPatientOuterClass.ProtoPatient patient) {
                    Patient.setPatient(patient);
                }
            });

            Model.sendRequest(Integer.parseInt(NumberInput.getText()));
//            Model.sendString(NumberInput.getText());
        }catch (Exception io){
            io.printStackTrace();
        }



    }










}

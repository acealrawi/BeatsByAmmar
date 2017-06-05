package MVC;

import Client.Patient;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import proto.ProtoPatientOuterClass;
import Client.AbstractDataHandler;


public class Controller extends AbstractDataHandler {

    View view = new View();

    @FXML
    TextField NumberInput;







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
            System.out.println(NumberInput.getText());
            Model.sendRequest(Integer.parseInt(NumberInput.getText()));
        }catch (Exception io){
            io.printStackTrace();
        }



    }
}

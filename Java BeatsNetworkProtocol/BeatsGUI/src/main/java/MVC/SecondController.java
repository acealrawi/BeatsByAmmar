package MVC;

import Client.AbstractDataHandler;
import Client.Patient;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import proto.ProtoPatientOuterClass;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Gebruiker on 5/25/2017.
 */
public class SecondController extends AbstractDataHandler implements Initializable {
    View view = new View();

    @FXML
    private Label NameLabel;

    @FXML
    private Label AgeLabel;

    @FXML
    private Label SportLabel;

    @FXML
    private Label PatientNumberLabel;

    @FXML
    private Button BackButton;

    @FXML
    private Label GenderLabel;

    @FXML
    private Label WeightLabel;





    @FXML
    void getScreenThree() {
        try{
            Parent secondParent = FXMLLoader.load(getClass().getClassLoader().getResource("FxmlScreens/ThirdScreen.fxml"));
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

    @FXML
    void getScreenOne() {

        try{

            Parent secondParent = FXMLLoader.load(getClass().getClassLoader().getResource("FxmlScreens/FirstScreen.fxml"));
            Stage secondStage = view.getStage();

            secondStage.setScene(new Scene(secondParent, 500, 350));
            secondStage.setTitle("Saxion hospital");
            secondStage.setResizable(false);
            secondStage.show();


        }catch (IOException io){
            io.printStackTrace();
        }

    }

    public void initialize(URL location, ResourceBundle resources) {

        updateSecondScreen(Patient.getPatient());

    }
    public void updateSecondScreen(final ProtoPatientOuterClass.ProtoPatient patient){
        Platform.runLater(new Runnable() {
            public void run() {
                if (patient != null) {
                    System.out.println("not nullllll");
                    System.out.println(patient.getAge());
                    NameLabel.setText(patient.getName());
                    AgeLabel.setText(String.valueOf(patient.getAge()));
                    PatientNumberLabel.setText(String.valueOf(patient.getPatientNumber()));

                }
                else{
                    System.out.println("ipdate null");
                }

            }
        });
    }
}


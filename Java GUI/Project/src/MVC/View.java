package MVC;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class View extends Application {

    private static Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent firstParent = FXMLLoader.load(getClass().getClassLoader().getResource("FirstScreen/FirstScreen.fxml"), null, new JavaFXBuilderFactory());
        Scene firstScene = new Scene(firstParent, 500, 350);
        primaryStage.setTitle("Saxion hospital");
        primaryStage.setScene(firstScene);
        primaryStage.setResizable(false);

        stage = primaryStage;
        primaryStage.show();
    }

    public static Stage getStage(){
        return stage;
    }


    public static void main(String[] args) {
        launch(args);
    }

}

package sample;

import GardenCode.GardenView;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.event.ActionEvent;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    public static Stage stage;
    public static Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception{

        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/sample/sample.fxml"));
        stage.setTitle("Garden Simulator");
        scene = new Scene(root,1000,600);
        stage.setScene(scene);
        stage.show();
        Controller.initGrid();



    }



    }


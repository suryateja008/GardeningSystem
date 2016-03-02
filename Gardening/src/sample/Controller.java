package sample;

import GardenCode.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
   public final static GardenView gardenView = new GardenView();
   public static Button clickedButton;
    public static Stage controllerstage;

//    public Controller(GardenView gardenView){
//        this.gardenView = gardenView;
//    }

      @FXML
      public Label currentTime;
      @FXML
      public Label countDownLabel;
      @FXML
      public Label currTemperature;
      @FXML
      public Label sprinklerLbl;
      @FXML
      public Label heaterSwitchLbl;
      @FXML
      public TextArea LoggerSection;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            // Time
            currentTime.textProperty().bind(gardenView.gardenTime.getJfxTime());
            // COuntDown
            countDownLabel.textProperty().bind(RandomEvents.jfxCountDown);
            // Temp
            currTemperature.textProperty().bind(Climate.instance.getJfxCurrTemp());
            // sprinkler Label
            sprinklerLbl.textProperty().bind(SprinklerSystem.jfxautoSprinkler);
            // Heater Label
            heaterSwitchLbl.textProperty().bind(HeaterSystem.jfxautoHeater);
            // Logger
            LoggerSection.textProperty().bind(Logger.jfxLogger);


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }



    public void stopButtonHandler()
    {
        gardenView.unLoadEvents();
        Main.stage.close();
    }

    public void sprinkerSwitchBtn()
    {
        SprinklerSystem.sprinklerSwitch();
    }

    public void heaterSwitch()
    {
        HeaterSystem.heaterSwitch();
    }

    public void incMaxBtn(){
          HeaterSystem.incMaxTemp();
    }

    public void decMaxBtn(){
          HeaterSystem.decMaxTemp();
    }

    public void incMinBtn(){
          HeaterSystem.incMinTemp();
    }

    public void decMinBtn(){
          HeaterSystem.decMinTemp();
    }

    public void mowLawn(){ Gardner.instance.mow();}

    public void popupBtn(ActionEvent event)
    {
        Parent root;
        clickedButton = (Button) event.getSource();
        if(clickedButton.getText()=="Sprinkler") {
            try {
                controllerstage = new Stage();
                root = FXMLLoader.load(getClass().getResource("/sample/sprinkler.fxml"));
                controllerstage.setScene(new Scene(root));
                controllerstage.initModality(Modality.APPLICATION_MODAL);
                controllerstage.initOwner(currentTime.getScene().getWindow());
                controllerstage.showAndWait();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
        {
            try {
                controllerstage = new Stage();
                root = FXMLLoader.load(getClass().getResource("/sample/replacer.fxml"));
                controllerstage.setScene(new Scene(root));
                controllerstage.initModality(Modality.APPLICATION_MODAL);
                controllerstage.initOwner(currentTime.getScene().getWindow());
                controllerstage.showAndWait();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void initGrid()
    {

        for(int i=0;i<gardenView.gardenGrid.length;i++)
            for(int j=0;j<gardenView.gardenGrid[0].length;j++)
                    styleButtons(i,j);



    }

    public static void styleButtons(int i,int j)
    {
        SystemInterface s;
        s=gardenView.gardenGrid[i][j];
        Button btn = (Button) Main.scene.lookup("#gridbtn" + i + "" + j);
        btn.setText(getInstanceName(s));
        btn.setStyle("-fx-background-color: " + getInstanceColor(s) + ";-fx-background-radius: 0;-fx-text-fill: #f7f7f7");

    }

    public static String getInstanceName(SystemInterface s)
    {
         if(s instanceof GardenSprinkler)
            return "Sprinkler";
        else if(s instanceof GardenHeater)
             return "Heater";
        else if(s instanceof Rose)
             return "Rose";
        else if(s instanceof Grass)
             return "Grass";
         else if(s instanceof Carrot)
             return "Carrot";

        return " ";
    }

    public static String getInstanceColor(SystemInterface s)
    {
        if(s instanceof GardenSprinkler)
            return "#c1f7ff";
        else if(s instanceof GardenHeater)
            return "#ff9b35";
        else if(s instanceof Rose)
            return "#ff6d6d";
        else if(s instanceof Grass)
            return "#8dff35";
        else if(s instanceof Carrot)
            return "#c75d23";

        return "c10013";
    }
}

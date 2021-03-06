package sample;

import GardenCode.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;

import javax.naming.ldap.Control;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by suryaduggi on 2/21/16.
 */
public class replacerController implements Initializable {
    @FXML
    public ComboBox replaceOptions;



    public static String replaceValue="Grass";
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        replaceOptions.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                replaceValue = newValue.toString();
            }
        });

    }

    public void changeSubmit()
    {
        String id = Controller.clickedButton.getId().replace("gridbtn","");
        int i = Integer.parseInt(id.charAt(0) + "");
        int j = Integer.parseInt(id.charAt(1) + "");
        GardenView.gardenGrid[i][j].stop();
        replace(replaceValue, GardenView.gardenGrid, i, j);
        Controller.controllerstage.close();

    }




    public void replace(String s,SystemInterface[][] gardenGrid,int i,int j)
    {

        switch(s)
        {
            // Grass
            case "Grass":
                gardenGrid[i][j] = new Grass(i,j);
                break;
            // Sprinkler
            case "Sprinkler":
                gardenGrid[i][j] = new GardenSprinkler(i,j,30);
                break;
            //Rose
            case "Rose":
                gardenGrid[i][j] = new Rose(i,j);
                break;
            case "Heater":
                gardenGrid[i][j] = new GardenHeater(i,j);
                break;
            case "Carrot":
                gardenGrid[i][j] = new Carrot(i,j);
                break;
            // grass
            default:
                gardenGrid[i][j] = new Grass(i,j);
                break;
        }

        Controller.styleButtons(i,j);

    }
}

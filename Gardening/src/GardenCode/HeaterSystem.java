package GardenCode;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sun.rmi.runtime.Log;

/**
 * Created by suryaduggi on 2/19/16.
 */
public abstract class HeaterSystem extends SystemControlls {

    // For heater
    public static boolean autoHeater = true;
    public static StringProperty jfxautoHeater= new SimpleStringProperty("Auto Heater is ON");
    protected static int minTemp = 18;
    protected static int maxTemp = 26;

    public static void setJfxautoHeater(String data)
    {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                jfxautoHeater.set(data);
            }
        });
    }

    public static void heaterSwitch()
    {
        autoHeater=!autoHeater;
        if(autoHeater)
        {

            setJfxautoHeater("Auto Heater is ON");
            Logger.log("Auto Heater is ON");
        }
        else
        {
            setJfxautoHeater("Auto Heater is OFF");
            Logger.log("Auto Heater is OFF");
        }
    }

    public static void incMinTemp() {
        if(HeaterSystem.minTemp<21) {
            HeaterSystem.minTemp++;
            Logger.log(String.format("Min temp %d", minTemp));
        }
        else
            Logger.log(String.format("Min temp cannot exceed More than 21"));
    }

    public static void decMinTemp() {
        if(HeaterSystem.minTemp>18) {
            HeaterSystem.minTemp--;
            Logger.log(String.format("Min temp %d",minTemp));
        }
        else
            Logger.log(String.format("Min temp cannot fall below than 18"));
    }

    public static void incMaxTemp() {
        if(HeaterSystem.maxTemp<29) {
            HeaterSystem.maxTemp++;
            Logger.log(String.format("Max temp %d",maxTemp));
        }
        else
            Logger.log(String.format("Max temp cannot exceed More than 29"));
    }

    public static void decMaxTemp() {
        if(HeaterSystem.maxTemp>25) {
            HeaterSystem.maxTemp--;
            Logger.log(String.format("Max temp %d",maxTemp));
        }
        else
            Logger.log(String.format("Max temp cannotfall below than 25"));
    }
}

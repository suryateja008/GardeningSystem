package GardenCode;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by suryaduggi on 2/19/16.
 */
public abstract class SprinklerSystem extends SystemControlls{

    // For sprinkler
    public static boolean autoSprinkler = true;
    public static StringProperty jfxautoSprinkler = new SimpleStringProperty("Auto Sprinkler is ON");
    public static long autoSprinklerOffDate = 0;

    public static void setJfxautoSprinkler(String data)
    {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                jfxautoSprinkler.set(data);
            }
        });
    }

    public static void sprinklerSwitch()
    {
        autoSprinkler=!autoSprinkler;
        if(autoSprinkler)
        {
            setJfxautoSprinkler("Auto Sprinkler is ON");
            Logger.log("Auto Sprinkler is ON");
        }
        else
        {
            setJfxautoSprinkler("Auto Sprinkler is OFF");
            Logger.log("Auto Sprinkler is OFF");
            autoSprinklerOffDate = GardenTime.getDays();
        }
    }

    public void setStartMIN(int startMIN) {
        this.startMIN = startMIN;
        Logger.log(String.format("Sprinker Min time set for Row %d and Col %d to %dM",positionRow,positionCol,startMIN));
    }


    public void setStartHours(int startHours) {
        this.startHours = startHours;
        Logger.log(String.format("Sprinker Hour time set for Row %d and Col %d to %dH",positionRow,positionCol,startHours));
    }
}

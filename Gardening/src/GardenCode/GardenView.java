package GardenCode;

import com.oracle.tools.packager.Log;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.lang.Override;import java.lang.System;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by suryaduggi on 2/10/16.
 */
public class GardenView implements SystemInterface {

    private static boolean started;
    public static GardenTime gardenTime;
    public static StringProperty jfxviewGarden;

    public static SystemInterface[][] gardenGrid;

    private String[][] gridLayout = {
            {"Sprinkler","Grass","Grass","Carrot","Sprinkler"},
            {"Grass","Grass","Grass","Carrot","Carrot"},
            {"Rose","Grass","Heater","Grass","Rose"},
            {"Grass","Grass","Grass","Grass","Grass"},
            {"Sprinkler","Grass","Grass","Grass","Sprinkler"},
    };


    int gridRow = gridLayout.length;
    int gridCol = gridLayout[0].length;


   public GardenView()
    {
        started = false;
        jfxviewGarden = new SimpleStringProperty(" ");
        setjfxviewGarden(viewOfGarden());
        gardenTime = new GardenTime();
        gardenGrid = new SystemInterface[gridRow][gridCol];
        start();
    }

    public void start()
    {
         if(!isStarted())
         {
             started = true;
             loadEvents();
         }
    }


    public boolean isStarted()
    {
        return started;
    }

    public void stop()
    {
        if(isStarted())
        {
            started = false;
            unLoadEvents();
        }

    }

    private void initGrid()
    {
        for(int i=0;i<gridRow;i++)
            for(int j=0;j<gridCol;j++)
            {
                switch(gridLayout[i][j])
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
            }

    }

    public void loadEvents()
    {
        gardenTime.start();
        initGrid();
        RandomEvents.randomEventGenerator.start();

    }

    public void unLoadEvents()
    {
        gardenTime.stop();
        RandomEvents.randomEventGenerator.currentEvent.stop();
        RandomEvents.randomEventGenerator.stop();

        Climate.instance.stop();

        for(int i=0;i<gridRow;i++)
            for(int j=0;j<gridCol;j++)
            {
                    gardenGrid[i][j].stop();
            }
        Logger.LoggerClose();

    }

    public  String viewOfGarden()
    {
        StringBuilder garden = new StringBuilder();
        for(int i=0;i<gridRow;i++) {
            for (int j = 0; j < gridCol; j++)
                garden.append(String.format(gridLayout[i][j]+"%"+(12-gridLayout[i][j].length())+"s"," "));

            garden.append("\n");
        }


        return garden.toString();
    }

    public static SystemInterface[][] getGrid()
    {
         return gardenGrid;
    }

    public static void setjfxviewGarden(String data)
    {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                jfxviewGarden.set(data);
            }
        });
    }

}

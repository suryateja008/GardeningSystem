package GardenCode;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by suryaduggi on 2/17/16.
 */
public class Climate implements SystemInterface {

    public  int currentTemp = 0;
    private int delay = 0;
    private int Secinterval = 4;
    protected ScheduledExecutorService bgthread;
    public StringProperty jfxCurrTemp;
    public static Climate instance = new Climate();

   private Climate()
    {
        this.jfxCurrTemp = new SimpleStringProperty(" ");
        this.currentTemp = 25;
        start();
    }

    public void setCurrentTemp(int currentTemp) {
        this.currentTemp = currentTemp;
    }

    @Override
    public void start() {

        this.bgthread = Executors.newSingleThreadScheduledExecutor();

        this.bgthread.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if(currentTemp<29)
                     currentTemp+= (int) (Math.random()*4-2);
                else
                     currentTemp-=(int) (Math.random()*2);

                Logger.log(String.format("Current Temp is %d --> %s",currentTemp,GardenTime.instance.getLogTime()));
                setJfxCurrTemp("Temperature " + currentTemp +"\u2103");
            }

        }, delay, Secinterval, TimeUnit.SECONDS);


    }

    @Override
    public void stop()
    {
        bgthread.shutdown();
    }

    public void setJfxCurrTemp(String data)
    {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                jfxCurrTemp.set(data);
            }
        });
    }

    public StringProperty getJfxCurrTemp()
    {
        return this.jfxCurrTemp;
    }
}

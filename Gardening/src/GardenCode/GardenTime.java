package GardenCode;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sample.Controller;

import java.lang.Math;import java.lang.String;
import java.lang.reflect.Executable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by suryaduggi on 2/10/16.
 */
public class GardenTime implements SystemInterface {

    private static long hours;
    private static long min;
    private static long sec;
    private static   long days;
    private static final long delay = 0;
    private static final long interval = 1;
    private ScheduledExecutorService bgthread;
    public StringProperty jfxTime;
    public static GardenTime instance = new GardenTime();

    public GardenTime()
    {
        this.jfxTime = new SimpleStringProperty(" ");
        this.hours = 0;
        this.min=0;
        this.sec=0;
        this.days = 1;
    }

    public void incSeconds()
    {
        sec += 60;
        if(sec%60==0)
        {
            this.min += sec/60;
            this.sec = 0;
        }

        if(min%60==0)
        {
            this.hours += this.min/60;
            this.min=0;
        }

        if(hours%24==0)
        {
            this.days += hours/24;
            this.hours = 0;
        }
        setJfxTime("Day "+getDays()+" "+getHours() + "h:" + getMin() + "m:" + getRandomSec()+"s");
    }

    public void start()
    {
        this.bgthread = Executors.newSingleThreadScheduledExecutor();
        bgthread.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                incSeconds();
            }
        }, delay, interval, TimeUnit.SECONDS);
    }

    @Override
    public void stop() {
        bgthread.shutdown();
    }

    public int getRandomSec()
    {
        return (int)(Math.random()*60+1);
    }

    public long getHours()
    {
        return hours;
    }

    public long getMin()
    {
        return min;
    }

    public long getSec()
    {
        return sec;
    }

    public static  long getDays()
    {
        return days;
    }

    public String getLogTime(){
        return "Day "+getDays()+" "+getHours() + "h:" + getMin() + "m:" + getRandomSec()+"s";
    }


    //jfx
    public StringProperty getJfxTime()
    {
        return this.jfxTime;
    }

    public void setJfxTime(String newTime){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                jfxTime.set(newTime);
            }
        });
    }

}

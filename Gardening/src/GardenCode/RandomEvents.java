package GardenCode;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by suryaduggi on 2/15/16.
 */
public abstract class RandomEvents implements SystemInterface {
    protected int startDay;
    protected int startHour;
    protected int startMin;
    protected int spanTime;
    protected long Secinterval = 1;
    protected long delay = 0;
    protected boolean eventIsOn = false;
    protected int affectedTime=0;
    protected static boolean affected=false;
    protected ScheduledExecutorService bgthread;
    public static RandomEventGenerator randomEventGenerator = new RandomEventGenerator();
    public static StringProperty jfxCountDown = new SimpleStringProperty(" ");;

    protected abstract void randomSetter();

    public abstract String WarningMessage();

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getStartMin() {
        return startMin;
    }

    public void setStartMin(int startMin) {
        this.startMin = startMin;
    }

    public int getSpanTime() {
        return spanTime;
    }

    public void setSpanTime(int spanTime) {
        this.spanTime = spanTime;
    }

    public boolean isEventIsOn() {
        return eventIsOn;
    }

    public void setEventIsOn(boolean eventIsOn) {
        this.eventIsOn = eventIsOn;
    }

    public int getAffectedTime() {
        return affectedTime;
    }

    public void setAffectedTime(int affectedTime) {
        this.affectedTime = affectedTime;
    }

    public static boolean isAffected() {
        return affected;
    }

    public abstract String countDown();

    public void setJfxCountDown(String data)
    {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                jfxCountDown.set(data);
            }
        });
    }

    public StringProperty getJfxCountDown()
    {
        return jfxCountDown;
    }


}

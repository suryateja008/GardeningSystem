package GardenCode;


import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by suryaduggi on 2/14/16.
 */
public abstract class SystemControlls implements SystemInterface {
    final static GardenTime gardenTime = new GardenTime();;
    final long delay = 0;
    final long MSinterval = 500;
    protected int startMIN;
    protected int startHours;
    protected int runTime;
    protected int positionRow;
    protected int positionCol;
    protected int tempRuntime;
    protected ScheduledExecutorService bgthread;
    protected boolean isOn;

    public int getStartMIN() {
        return startMIN;
    }

    public void setStartMIN(int startMIN) {
        this.startMIN = startMIN;
    }

    public int getStartHours() {
        return startHours;
    }

    public void setStartHours(int startHours) {
        this.startHours = startHours;
    }

    public int getRunTime() {
        return runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    public int getPositionRow() {
        return positionRow;
    }

    public void setPositionRow(int positionRow) {
        this.positionRow = positionRow;
    }

    public int getPositionCol() {
        return positionCol;
    }

    public void setPositionCol(int positionCol) {
        this.positionCol = positionCol;
    }

    public RandomEvents getCurrentEvent()
    {
        return RandomEvents.randomEventGenerator.currentEvent;
    }



}

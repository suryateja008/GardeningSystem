package GardenCode;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by suryaduggi on 2/14/16.
 */
public class GardenHeater extends HeaterSystem {

   protected float incHeat;

    public GardenHeater(int row, int col)
    {
        this.isOn = false;
        this.positionCol=col;
        this.positionRow=row;
        Logger.log(String.format("Heater at Row %d and COl %d is Place and Started",positionRow,positionCol));
        start();
    }

    public float getIncHeat() {
        return incHeat;
    }

    public void setIncHeat(float incHeat) {
        this.incHeat = incHeat;
    }

    public GardenHeater()
    {
       this(0,0);
    }

    @Override
    public void start()
    {
        this.bgthread = Executors.newSingleThreadScheduledExecutor();

        bgthread.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                // If it's not on then check time for on
                if (HeaterSensor.getTemperature() < minTemp && !autoHeater) {
                    Logger.log(String.format("Turning On Heater as temp drops below minimum temp --> %s", GardenTime.instance.getLogTime()));
                    heaterSwitch();
                }

                heaterLogic();

            }
        }, delay, 2, TimeUnit.SECONDS);
    }

    @Override
    public void stop()
    {
        Logger.log(String.format("Heater at Row = %d and Col = %d is Removed --> %s", positionRow, positionCol, GardenTime.instance.getLogTime()));
        bgthread.shutdown();
    }

    public void heaterLogic()
    {
        if (!isOn) {
            if (HeaterSensor.getTemperature() < minTemp && autoHeater) {
                isOn = true;
                Logger.log(String.format("Heater at Row = %d and Col = %d is ON --> %s", positionRow, positionCol, GardenTime.instance.getLogTime()));
            }
        } else {
            if (HeaterSensor.getTemperature() >maxTemp && isOn) {
                Logger.log(String.format("Heater at Row = %d and Col = %d is OFF --> %s", positionRow, positionCol, GardenTime.instance.getLogTime()));
                isOn = false;
            } else {
                 Climate.instance.setCurrentTemp(HeaterSensor.getTemperature()+(int) (Math.random()*4));
            }

        }
    }

}

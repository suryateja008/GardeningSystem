package GardenCode;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by suryaduggi on 2/13/16.
 */
public class GardenSprinkler extends SprinklerSystem{

    public GardenSprinkler(int row, int col,int runTime)
    {
        this.startMIN=15;
        this.startHours=0;
        this.runTime=runTime;
        this.isOn = false;
        this.positionCol=col;
        this.positionRow=row;
        Logger.log(String.format("Sprinkler at Row %d and COl %d is Place and Started",positionRow,positionCol));
        start();

    }

    public GardenSprinkler()
    {
        this(0, 0, 30);
    }

    @Override
    public void start()
    {
        this.bgthread = Executors.newSingleThreadScheduledExecutor();

        bgthread.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (!autoSprinkler) {
                    if (GardenTime.getDays() - autoSprinklerOffDate >= 2) {
                        Logger.log(String.format("AutoSprinkler Was OFF was more than 2 Day. Turning Back ON --> %s",GardenTime.instance.getLogTime()));
                        sprinklerSwitch();
                    }
                }
                SprinklerLogic();
            }
        }, delay, MSinterval, TimeUnit.MILLISECONDS);
    }

    @Override
    public void stop()
    {
        Logger.log(String.format("Sprinkler at Row = %d and Col = %d is Removed --> %s", positionRow, positionCol,GardenTime.instance.getLogTime()));
        bgthread.shutdown();
    }

    private void SprinklerLogic()
    {

        // If it's not on
        if (!isOn) {
            if (gardenTime.getMin() == startMIN && gardenTime.getHours() == startHours) {
                if(autoSprinkler) {
                    boolean rain = !(getCurrentEvent() instanceof Rain) || ((getCurrentEvent() instanceof Rain) && !getCurrentEvent().isAffected());
                    boolean snow = !(getCurrentEvent() instanceof Snow) || ((getCurrentEvent() instanceof Snow) && !getCurrentEvent().isAffected());
                    if (rain && snow) {
                        isOn = true;
                        Logger.log(String.format("Sprinkler at Row = %d and Col = %d is ON --> %s", positionRow, positionCol, GardenTime.instance.getLogTime()));
                    } else {
                        Logger.log(String.format("Sprinkler at Row = %d and Col = %d is OFF due to %s  --> %s", positionRow, positionCol, getCurrentEvent().WarningMessage(), GardenTime.instance.getLogTime()));
                    }
                }
                else
                {
                    Logger.log(String.format("Sprinkler at Row = %d and Col = %d is manually Turned OFF --> %s", positionRow, positionCol, GardenTime.instance.getLogTime()));
                }
            }
        } else { // If sprinkler is on
            if (runTime <= tempRuntime) {
                tempRuntime = 0;
                Logger.log(String.format("Sprinkler at Row = %d and Col = %d is OFF --> %s", positionRow, positionCol, GardenTime.instance.getLogTime()));
                isOn = false;
            } else {
                tempRuntime++;
            }

        }

    }




}

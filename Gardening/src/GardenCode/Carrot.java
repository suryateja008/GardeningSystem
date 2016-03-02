package GardenCode;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by suryaduggi on 2/16/16.
 */
public class Carrot extends GardenPlants {

    public Carrot()
    {
        this.lifeSpan = (int) (Math.random()*12+5);
        this.height = (int) (Math.random()*4+1);
    }


    public Carrot(int r,int c)
    {
        this();
        this.positionRow = r;
        this.positionCol = c;
        Logger.log(String.format("Carrot at Row %d and COl %d is Place",positionRow,positionCol));
        start();

    }


    @Override
    public void start()
    {
        this.bgthread = Executors.newSingleThreadScheduledExecutor();

        this.bgthread.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                // show the growth for half a day
                if (tempCount == 360) {
                    height += .1f;
                    lifeSpan -= .5f;
                    Logger.log(String.format("Carrot at Row = %d and Col = %d Height is %.1f inc and Remaining Lifespan is %.0f days --> %s", positionRow, positionCol, height, lifeSpan, GardenTime.instance.getLogTime()));
                    tempCount = 0;
                }

                if (lifeSpan <= 0) {
                    randomSetting();
                }

                tempCount++;
            }
        }, delay, Secinterval, TimeUnit.SECONDS);
    }

    @Override
    public void stop()
    {
        Logger.log(String.format("Carrot at Row = %d and Col = %d is Dead --> %s", positionRow, positionCol, GardenTime.instance.getLogTime()));
        this.bgthread.shutdown();
    }


    public void randomSetting()
    {
        Logger.log(String.format("Carrot at Row = %d and Col = %d is replaced by Gardner",positionRow,positionCol));
        this.lifeSpan = (int) (Math.random()*12+5);
        this.height = (int) (Math.random()*4+1);
    }
}

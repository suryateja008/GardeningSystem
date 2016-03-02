package GardenCode;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by suryaduggi on 2/14/16.
 */
public class Grass extends GardenPlants {


    public Grass()
    {
       this.lifeSpan = (int) (Math.random()*10+5);
       this.height = (int) (Math.random()*2+1);

    }

    public Grass(int r,int c)
    {
        this();
        this.positionRow = r;
        this.positionCol = c;
        Logger.log(String.format("Grass at Row %d and COl %d is Place",positionRow,positionCol));
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
                if (tempCount == 720) {
                    height += .1f;
                    lifeSpan -= .5f;
                    Logger.log(String.format("Grass at Row = %d and Col = %d Height is %.1f inc and Remaining Lifespan is %.0f days --> %s", positionRow, positionCol, height, lifeSpan, GardenTime.instance.getLogTime()));
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
        Logger.log(String.format("Grass at Row = %d and Col = %d is Dead --> %s",positionRow, positionCol,GardenTime.instance.getLogTime()));
        this.bgthread.shutdown();
    }

    public void randomSetting()
    {
        Logger.log(String.format("Grass at Row = %d and Col = %d is replaced by Gardner",positionRow,positionCol));
        this.lifeSpan = (int) (Math.random()*10+5);
        this.height = (int) (Math.random()*2+1);;
    }
}



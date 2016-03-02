package GardenCode;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by suryaduggi on 2/16/16.
 */
public class RandomEventGenerator implements SystemInterface {
    int numOfEvents = 5-1;
    int delay = 0;
    int interval = 2;
    static boolean currentEventOn;
    public static RandomEvents currentEvent;
    protected ScheduledExecutorService bgthread;

    public RandomEventGenerator()
    {
          currentEventOn = false;
    }



    @Override
    public void start() {

        this.bgthread = Executors.newSingleThreadScheduledExecutor();
        bgthread.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if(!currentEventOn)
                {
                    currentEvent = factory();
                    currentEventOn = true;
                }
            }
        },delay,interval, TimeUnit.SECONDS);

    }

    @Override
    public void stop() {
       bgthread.shutdown();
       Logger.log(String.format("Random even Stopped"));
    }

    public RandomEvents factory()
    {
        int rand = (int) Math.round(Math.random()*numOfEvents);
        switch (rand)
        {
            case 0:
                return new Rain();
            case 1:
                return new Snow();
            case 2:
                return new Pest();
            case 3:
                return new SprinklerCrash();
            case 4:
                return new HeaterCrash();
            default:
                return new Pest();
        }
    }
}

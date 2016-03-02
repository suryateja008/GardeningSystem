package GardenCode;


import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by suryaduggi on 2/15/16.
 */
public class SprinklerCrash extends RandomEvents {

    public SprinklerCrash()
    {
        randomSetter();
        start();
    }



    @Override
    public void start()
    {
        this.bgthread = Executors.newSingleThreadScheduledExecutor();

        bgthread.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Logger.log(String.format(countDown()));
                setJfxCountDown(countDown());
                //count down for start
                if(startMin<=0 && startHour>0)
                {
                    startHour--;
                    startMin=60;
                    if(startHour==0 && startDay>0)
                    {
                        startDay--;
                        startHour=24;
                    }
                }
                else if(startMin>0)
                {
                    startMin--;
                }


                // check for start
                if(startDay==0 && startHour ==0 && startMin ==0 && !eventIsOn)
                {
                    Logger.log(String.format("SprinklerCrash  --> %s", GardenTime.instance.getLogTime()));
                    sprinklerCrash(GardenView.getGrid());
                    stop();
                }


            }
        }, delay, Secinterval, TimeUnit.SECONDS);
    }

    public static void sprinklerCrash(SystemInterface[][] gardenGrid)
    {
        SystemInterface temp;
        for(int i=0;i<gardenGrid.length;i++)
            for(int j=0;j<gardenGrid[0].length;j++)
            {
                temp = gardenGrid[i][j];
                if(temp instanceof GardenSprinkler)
                {
                    if(Math.round(Math.random()*1)==1){
                        Logger.log(String.format("Sprinkler at Row %d and Col %d is Crashed and replaced",i,j));
                        gardenGrid[i][j] = new GardenSprinkler(i,j,30);
                    }
                }
            }

    }

    @Override
    public void stop()
    {
        randomEventGenerator.currentEventOn=false;
        Logger.log(String.format("SprinklerCrash Even is Stopped --> %s", GardenTime.instance.getLogTime()));
        bgthread.shutdown();
    }


    @Override
    protected void randomSetter() {

        this.startDay = (int) ((Math.random()*3)+2);
        this.startHour = (int) (Math.random()*23)+1;
        this.startMin = (int) (Math.random()*59)+1;
    }

    @Override
    public String WarningMessage() {
        return "SprinklerCrash";
    }

    @Override
    public String countDown() {
        return String.format("SprinklerCrash Event %dd %dh %dm", startDay, startHour,startMin);
    }
}

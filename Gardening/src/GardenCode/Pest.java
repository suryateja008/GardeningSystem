package GardenCode;


import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by suryaduggi on 2/15/16.
 */
public class Pest extends RandomEvents {

    public Pest()
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
                if(startDay==0 && startHour ==0 && startMin ==0)
                {
                    Logger.log(String.format("Pest Attack --> %s", GardenTime.instance.getLogTime()));
                    pestDisaster(GardenView.getGrid());
                    stop();
                }


            }
        }, delay, Secinterval, TimeUnit.SECONDS);
    }

    public static void pestDisaster(SystemInterface[][] gardenGrid)
    {
        SystemInterface temp;
        for(int i=0;i<gardenGrid.length;i++)
            for(int j=0;j<gardenGrid[0].length;j++)
            {
                temp = gardenGrid[i][j];
                if(temp instanceof Grass || temp instanceof Rose || temp instanceof Carrot)
                {
                    ((GardenPlants) temp).setLifeSpan(((GardenPlants) temp).getLifeSpan()-(int)(Math.random()*3+1));
                    Logger.log(String.format("New Life Span for Entity at Row %d and Col %d is %f",((GardenPlants) temp).getPositionRow(),((GardenPlants) temp).getPositionCol(),((GardenPlants) temp).getLifeSpan()));
                }
            }

    }

    @Override
    public void stop()
    {
        randomEventGenerator.currentEventOn=false;
        Logger.log(String.format("Pest Even is Stopped --> %s", GardenTime.instance.getLogTime()));
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
        return "Pest";
    }

    @Override
    public String countDown() {
        return String.format("Pest Event %dd %dh %dm", startDay, startHour,startMin);
    }
}

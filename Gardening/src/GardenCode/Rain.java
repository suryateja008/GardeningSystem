package GardenCode;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by suryaduggi on 2/15/16.
 */
public class Rain extends RandomEvents {

    public Rain()
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
                //count down for start
                setJfxCountDown(countDown());
                Logger.log(String.format(countDown()));
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
                if(startDay==0 && startHour ==0 && startMin ==0 && !eventIsOn && !affected)
                {
                    Logger.log(String.format("Rain Started ---> %s",GardenTime.instance.getLogTime()));
                    Climate.instance.setCurrentTemp(HeaterSensor.getTemperature() - (int) (Math.random() * 5));
                    eventIsOn = true;
                    affected=true;
                }

                // check for span
                if(eventIsOn)
                {
                    if(spanTime>0)
                    {
                        spanTime--;
                    }
                    else
                    {
                        Logger.log(String.format("Rain STOPPED --> %s",GardenTime.instance.getLogTime()));
                        eventIsOn = false;
                    }
                }

                if(affected && !eventIsOn)
                {
                    if(affectedTime>0)
                       affectedTime--;
                    else
                    {
                        affected=false;
                        stop();
                    }
                }

            }
        }, delay, Secinterval, TimeUnit.SECONDS);
    }

    @Override
    public void stop()
    {
        randomEventGenerator.currentEventOn=false;
        Logger.log(String.format("Rain Event is stopped --> %s",GardenTime.instance.getLogTime()));
        bgthread.shutdown();
    }


    @Override
    protected void randomSetter() {

        this.startDay = (int) ((Math.random()*3)+2);
        this.startHour = (int) (Math.random()*23)+1;
        this.startMin = (int) (Math.random()*59)+1;
        this.spanTime = (int) (Math.random()*360);
        this.affectedTime = 1440; //total for one day sprinklers are off

    }

    @Override
    public String WarningMessage() {
        return "Rain";
    }

    @Override
    public String countDown() {
        return String.format("Rain Event %dd %dh %dm",startDay,startHour,startMin);
    }
}

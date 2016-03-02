package GardenCode;

/**
 * Created by suryaduggi on 2/20/16.
 */
public class HeaterSensor {

    private HeaterSensor()
    {
    }

    public static int getTemperature()
    {
        return Climate.instance.currentTemp;
    }
}

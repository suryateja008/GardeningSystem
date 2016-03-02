package GardenCode;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * Created by suryaduggi on 2/18/16.
 */
public class Logger {
    private static FileWriter fileWriter;
    private static BufferedWriter bufferedWriter;
    public static StringProperty jfxLogger = new SimpleStringProperty(" ");
    private static StringBuilder sb = new StringBuilder(" ");

    private  Logger()
    {

    }


    public static void log(String data)
    {
        if(bufferedWriter==null)
        {
            try {
                fileWriter = new FileWriter("SuryaLog.log");
                bufferedWriter = new BufferedWriter(fileWriter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(bufferedWriter!=null)
        {
            try {
                sb.insert(0,data+"\n");
                setJfxLogger(sb.toString());
                bufferedWriter.write(data);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        }


    }

    public static void LoggerClose()
    {
        try {
            bufferedWriter.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void setJfxLogger(String data)
    {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                jfxLogger.set(data);
            }
        });
    }
}

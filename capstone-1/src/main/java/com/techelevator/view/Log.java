package com.techelevator.view;

import com.techelevator.model.Accounting;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Log {
    private File log = new File("log.txt");
    private static ArrayList<String> runningLog = new ArrayList<>();


    private static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    public static void logging(String event) {
        runningLog.add(getCurrentTimeStamp() + " " + event);
        saveLog();
    }

    private static void saveLog() {
        try {
            FileWriter writer = new FileWriter("log.txt", true);
            for (String str : runningLog) {
                writer.write(str + System.lineSeparator());
            }
            writer.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("File does not exist");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
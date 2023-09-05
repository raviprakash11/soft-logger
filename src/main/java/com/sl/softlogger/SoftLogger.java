package com.sl.softlogger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SoftLogger {
    private BufferedWriter writer;

    public SoftLogger(String logFileName) {
        try {
            writer = new BufferedWriter(new FileWriter(logFileName, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void log(LogLevel level, String message) {
        String formattedMessage = getFormattedLogMessage(level, message);
        writeLog(formattedMessage);
    }

    private String getFormattedLogMessage(LogLevel level, String message) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = dateFormat.format(new Date());
        return "[" + timestamp + "] [" + level + "] " + message;
    }

    private void writeLog(String message) {
        try {
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


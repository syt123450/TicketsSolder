package com.ticketSolder;

import org.junit.Ignore;
import org.junit.Test;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by ss on 2017/11/22.
 */

public class Generator {

    @Test
    @Ignore
    public void generateSBFast() {
        Calendar calendar = Calendar.getInstance();

        StringBuilder insertSQL = new StringBuilder();
        insertSQL.append("insert into fastschedule values ");

        int trainID = 1;

        for (int i = 6; i <= 21; i++) {

            calendar.set(Calendar.HOUR_OF_DAY, i);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);

            insertSQL.append("(");
            int hour = Integer.valueOf(new SimpleDateFormat("HH").format(calendar.getTime()));
            int minute = Integer.valueOf(new SimpleDateFormat("mm").format(calendar.getTime()));
//            String trainName = "SB" + String.format("%02d%02d", hour, minute);
//            insertSQL.append(trainName);

            insertSQL.append(trainID);
            trainID += 4;

            for (int j = 0; j < 6; j++) {

                insertSQL.append(", \"" + new Time(calendar.getTimeInMillis()) + "\"");

                minute = minute + 28;
                if (minute >= 60) {
                    hour++;
                    minute -= 60;
                }
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
            }
            insertSQL.append("), ");
        }

        insertSQL.delete(insertSQL.length() - 2, insertSQL.length() - 1);
        insertSQL.append(";");

        System.out.println(insertSQL);
    }

    @Test
    @Ignore
    public void generateNBFast() {

        Calendar calendar = Calendar.getInstance();

        StringBuilder insertSQL = new StringBuilder();
        insertSQL.append("insert into fastschedule values ");

        int trainID = 62;

        for (int i = 6; i <= 21; i++) {

            StringBuilder valueBuilder = new StringBuilder();

            calendar.set(Calendar.HOUR_OF_DAY, i);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);

            valueBuilder.append("),");
            int hour = Integer.valueOf(new SimpleDateFormat("HH").format(calendar.getTime()));
            int minute = Integer.valueOf(new SimpleDateFormat("mm").format(calendar.getTime()));
//            String trainName = "SB" + String.format("%02d%02d", hour, minute);
//            insertSQL.append(trainName);


            for (int j = 0; j < 6; j++) {

                valueBuilder.insert(0, ", \"" + new Time(calendar.getTimeInMillis()) + "\"");

                minute = minute + 28;
                if (minute >= 60) {
                    hour++;
                    minute -= 60;
                }
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
            }


            valueBuilder.insert(0, trainID);
            trainID += 4;

            valueBuilder.insert(0, "(");

            insertSQL.append(valueBuilder);
        }

        insertSQL.deleteCharAt(insertSQL.length() - 1);
        insertSQL.append(";");

        System.out.println(insertSQL);
    }

    @Test
    @Ignore
    public void generateSBSlow() {

        Calendar calendar = Calendar.getInstance();

        StringBuilder insertSQL = new StringBuilder();
        insertSQL.append("insert into slowschedule values ");

        int trainID = 1;

        for (int i = 6; i < 21; i++) {

            for (int j = 0; j < 60; j += 15) {

                calendar.set(Calendar.HOUR_OF_DAY, i);
                calendar.set(Calendar.MINUTE, j);
                calendar.set(Calendar.SECOND, 0);

                if (trainID % 4 != 1) {

                    insertSQL.append("(");

                    int hour = Integer.valueOf(new SimpleDateFormat("HH").format(calendar.getTime()));
                    int minute = Integer.valueOf(new SimpleDateFormat("mm").format(calendar.getTime()));

                    insertSQL.append(trainID);

                    for (int k = 0; k < 26; k++) {

                        insertSQL.append(", \"" + new Time(calendar.getTimeInMillis()) + "\"");

                        minute += 8;
                        if (minute >= 60) {
                            hour++;
                            minute -= 60;
                        }
                        calendar.set(Calendar.HOUR_OF_DAY, hour);
                        calendar.set(Calendar.MINUTE, minute);
                    }

                    insertSQL.append("), ");
                }

                trainID ++;
            }
        }

        insertSQL.delete(insertSQL.length() - 2, insertSQL.length() - 1);
        insertSQL.append(";");

        System.out.println(insertSQL);
    }

    @Test
    @Ignore
    public void generateNBSlow() {

        Calendar calendar = Calendar.getInstance();

        StringBuilder insertSQL = new StringBuilder();
        insertSQL.append("insert into slowschedule values ");

        int trainID = 62;

        for (int i = 6; i < 21; i++) {

            for (int j = 0; j < 60; j += 15) {

                calendar.set(Calendar.HOUR_OF_DAY, i);
                calendar.set(Calendar.MINUTE, j);
                calendar.set(Calendar.SECOND, 0);

                if (trainID % 4 != 2) {

                    StringBuilder valueBuilder = new StringBuilder();

                    valueBuilder.append("), ");

                    int hour = Integer.valueOf(new SimpleDateFormat("HH").format(calendar.getTime()));
                    int minute = Integer.valueOf(new SimpleDateFormat("mm").format(calendar.getTime()));

                    for (int k = 0; k < 26; k++) {

                        valueBuilder.insert(0, ", \"" + new Time(calendar.getTimeInMillis()) + "\"");

                        minute += 8;
                        if (minute >= 60) {
                            hour++;
                            minute -= 60;
                        }
                        calendar.set(Calendar.HOUR_OF_DAY, hour);
                        calendar.set(Calendar.MINUTE, minute);
                    }

                    valueBuilder.insert(0, trainID);
                    valueBuilder.insert(0, "(");

                    insertSQL.append(valueBuilder);
                }

                trainID ++;
            }
        }

        insertSQL.delete(insertSQL.length() - 2, insertSQL.length() - 1);
        insertSQL.append(";");

        System.out.println(insertSQL);
    }
}

package com.uharris.marvelapp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by uharris on 2/8/17.
 */

public class DateUtil {

    public static String DATE_BD_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
    public static String DATE_APP_FORMAT = "dd/MM/yyyy";

    public static String getDate(String date){
        SimpleDateFormat format = new SimpleDateFormat(DATE_BD_FORMAT);
        try {
            Date newDate = format.parse(date);

            SimpleDateFormat newFormat = new SimpleDateFormat(DATE_APP_FORMAT);

            return newFormat.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }
}

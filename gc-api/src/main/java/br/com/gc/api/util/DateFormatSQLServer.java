package br.com.gc.api.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatSQLServer {

    public static String format(Date date, String format){
        java.text.DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

}

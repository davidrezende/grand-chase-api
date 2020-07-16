package br.com.gc.api.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatSQLServer {

    public static String format(Date date){
        java.text.DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
}

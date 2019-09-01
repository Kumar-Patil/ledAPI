package com.ledVan.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Santosh Patil
 */
public class Util {

    public static String getFormatedDate(String dateStr) throws ParseException {
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = formatter1.parse(dateStr);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter = new SimpleDateFormat("dd MMMM yyyy");
        return formatter.format(date1);
    }

    public static String getYearAndMonth(String dateStr) throws ParseException {
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = formatter1.parse(dateStr);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        formatter = new SimpleDateFormat("yyyy-MM");
        return formatter.format(date1);
    }

    public static String getMonthYear(String dateStr) throws ParseException {
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = formatter1.parse(dateStr);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter = new SimpleDateFormat("MMMM yyyy");
        return formatter.format(date1);
    }
}

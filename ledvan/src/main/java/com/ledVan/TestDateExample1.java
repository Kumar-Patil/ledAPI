/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ledVan;

/**
 *
 * @author santopat
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDateExample1 {

    public static void main(String[] argv) throws ParseException {

        
        SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd");  
        Date date1=formatter1.parse("2019-09-01"); 
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(date1);

        formatter = new SimpleDateFormat("MMMM yyyy");
        strDate = formatter.format(date1);
        System.out.println("Date : " + strDate);

    }

}

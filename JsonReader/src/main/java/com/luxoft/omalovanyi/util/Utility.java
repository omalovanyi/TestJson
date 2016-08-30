package com.luxoft.omalovanyi.util;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by maoleks on 6/16/2016.
 */
public class Utility {
    private static final Logger logger = Logger.getLogger(Utility.class);

    private static SimpleDateFormat sdfDate;

    public static void setSdfDate(SimpleDateFormat sdfDate) {
        Utility.sdfDate = sdfDate;
    }

    /**

     * Convert String format to Date
     *
     * @return Date
     */
    public static Date stringToDate(String str) {
        Date result = null;
        try {
            result=sdfDate.parse(str);
        } catch (ParseException e) {
            logger.error("Failed converting String to Date: "+e.getMessage(),e);
            System.exit(-1);
        }
        return result;
    }

    /**
     * Convert Date to String in format
     *
     * @return String
     */
    public static String dateToString(Date date) {
        return sdfDate.format(date);
    }
}
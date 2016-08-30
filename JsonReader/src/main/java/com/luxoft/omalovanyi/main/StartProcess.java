package com.luxoft.omalovanyi.main;

import com.luxoft.omalovanyi.reader.JsonReaderException;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by maoleks on 6/10/2016.
 */
public class StartProcess {
    private static final Logger logger = Logger.getLogger(MainProcess.class);

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        MainProcess mainProcess = context.getBean(MainProcess.class);

        logger.info("Process JsonReader Started!");
        try {
            mainProcess.startProcess();
            mainProcess.showResult();
         } catch (JsonReaderException e) {
            logger.error("Failed process JsonReader --> "+e.getMessage(), e);
            System.exit(-1);
        }
        logger.info("Process JsonReader Ended Successfully!");
    }
}

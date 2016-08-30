package com.luxoft.omalovanyi.main;

import com.luxoft.omalovanyi.aop.logging.ShowTime;
import com.luxoft.omalovanyi.reader.JsonReader;
import com.luxoft.omalovanyi.reader.JsonReaderException;
import com.luxoft.omalovanyi.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

/**
 * Created by maoleks on 6/15/2016.
 */
@Service
public class MainProcess {

    @Autowired
    JsonReader jsonReader;

    @Value("${FormatDate}")
    private String formatDate;

    @ShowTime
    public void startProcess() throws JsonReaderException{
        Utility.setSdfDate(new SimpleDateFormat(formatDate));
        jsonReader.read();
        jsonReader.sort();
        jsonReader.save();
    }

    public void showResult() {
        jsonReader.showResult();
    }
}

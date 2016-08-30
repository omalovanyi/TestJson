package com.luxoft.omalovanyi.reader;

import com.luxoft.omalovanyi.aop.logging.ShowTime;
import com.luxoft.omalovanyi.dao.PlayerDAO;
import com.luxoft.omalovanyi.model.Player;
import com.luxoft.omalovanyi.parser.JsonFileParser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by maoleks on 6/10/2016.
 */
@Service
public class JsonReader {
    private static final Logger logger = Logger.getLogger(JsonReader.class);
    private final Object lock = new Object();
    ExecutorService exec = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    CompletionService<Boolean> completionService = new ExecutorCompletionService<>(exec);
    private List<Player> finalListPlayers = new ArrayList<>();

    @Autowired
    private FolderReader folderReader;
    @Autowired
    private JsonFileParser parser;
    @Autowired
    private PlayerDAO playerDAO;
    @Value("${CharSet}")
    private String charSet;
    @Value("${FilterDate}")
    private String filterDate;
    @Value("${FormatDate}")
    private String formatDate;

    @ShowTime
    public void read() throws JsonReaderException {
        try {
            int i = 0;

            for (File file : folderReader.getListFiles()) {
                i++;
                final File finalFile = file;
                completionService.submit(
                        new Callable<Boolean>()  {
                            private SimpleDateFormat sdfDate = new SimpleDateFormat(formatDate);
                            public Boolean call() throws JsonReaderException {
                                try {
                                    for (Player player : parser.parse(new InputStreamReader(new FileInputStream(finalFile), charSet)).getSheet().getListPlayers()) {
                                            if (sdfDate.parse(player.getDateBirthday()).after(sdfDate.parse(filterDate))) {

                                                synchronized (lock) {
                                                    finalListPlayers.add(player);
                                                }
                                            }
                                    }
                                } catch (UnsupportedEncodingException e) {
                                    throw new JsonReaderException("Incorrect CharSet --> "+e.getMessage(),e);
                                } catch (FileNotFoundException e) {
                                    throw new JsonReaderException("Incorrect path --> "+e.getMessage(),e);
                                } catch (ParseException e) {
                                    throw new JsonReaderException("Failed converting String to Date --> "+e.getMessage(),e);
                                } catch (Exception e) {
                                    throw new JsonReaderException("Failed in other --> "+e.getMessage(),e);
                                }
                                return true;
                            }
                        }
                );
            }

            for (int j = 0; j < i; j++) {
                completionService.take().get();
            }

        } catch (Exception e) {
            throw new JsonReaderException("Failed process in JsonReader.read() --> "+e.getMessage(),e);
        } finally {
            exec.shutdown();
        }
    }

    @ShowTime
    public void sort() {
        Collections.sort(finalListPlayers);
    }

    @ShowTime
    @Transactional
    public void save() throws JsonReaderException{
        try {
             playerDAO.clearListPlayers();
             playerDAO.insertListPlayers(finalListPlayers);
        } catch (Exception e) {
            throw new JsonReaderException("Failed process in JsonReader.save() --> " + e.getMessage(), e);
        }
    }

    public void showResult() {
        logger.info("COUNT PLAYERS = ["+finalListPlayers.size()+"]");
        logger.info("PLAYERS:");
        for (Player pl : finalListPlayers) {
            logger.info(pl.toString());
        }
    }
}

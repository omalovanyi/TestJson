package com.luxoft.omalovanyi.parser;

import com.google.gson.Gson;
import com.luxoft.omalovanyi.model.JsonFile;
import org.springframework.stereotype.Service;

import java.io.Reader;

/**
 * Created by maoleks on 6/10/2016.
 */

@Service
public class JsonFileParser {
    public JsonFile parse(Reader reader) {
        Gson gson = new Gson();
        return gson.fromJson(reader, JsonFile.class);
    }
}



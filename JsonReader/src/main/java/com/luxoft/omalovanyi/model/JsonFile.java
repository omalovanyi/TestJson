package com.luxoft.omalovanyi.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by maoleks on 6/14/2016.
 */

public class JsonFile {
    @SerializedName("sheets")
    private Sheet sheet;

    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }
}


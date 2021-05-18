package com.quantilink.dailylife.models;

import java.io.Serializable;
import java.util.List;

/**
 * Data package contains serialized json data which includes all the data lists and an ID from a google account
 */
public class DataPackage implements Serializable {
    String id;
    String jsonData;

    public DataPackage(String id, String jsonData) {
        this.id = id;
        this.jsonData = jsonData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    @Override
    public String toString() {
        return "DataPackage{" +
                "id='" + id + '\'' +
                ", jsonData='" + jsonData + '\'' +
                '}';
    }


}

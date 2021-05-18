package com.quantilink.dailylife.models;

/**
 * Response data package. Used with the get request in the dataAPI
 */
public class DataPackageResponse {
    private String id;
    private String jsonData;

    public DataPackage getDataPackage(){
        return new DataPackage(id, jsonData);
    }

    public String getId() {
        return id;
    }

    public String getJsonData() {
        return jsonData;
    }
}

package com.coscale.sdk.client.data;

import java.io.IOException;

import com.coscale.sdk.client.ApiClient;
import com.coscale.sdk.client.commons.Msg;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * CoScale API client used to insert data.
 * 
 * @author cristi
 *
 */
public class DataApi {

    /** ApiClient used to make HTTP requests */
    private final ApiClient api;

    /**
     * DataApi contructor.
     * 
     * @param api
     *            ApiClient.
     */
    public DataApi(ApiClient api) {
        this.api = api;
    }

    /**
     * Insert data into the data-store
     * 
     * @param data
     * @return
     * @throws IOException
     */
    public Msg insert(String subjectId, DataInsert data) throws IOException {
        return api.callWithAuth("POST", "/data/" + subjectId + '/', data, new TypeReference<Msg>() {
        });
    }
}

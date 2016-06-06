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
     * @return Msg containing the api response.
     * @throws IOException
     */
    public Msg insert(String subjectId, DataInsert data) throws IOException {
        return api.callWithAuth("POST", "/data/" + subjectId + '/', data, new MsgTypeReference());
    }

    /**
     * Copied from the Intellij code analysis:
     * A static inner class does not keep an implicit reference to its enclosing instance.
     * This prevents a common cause of memory leaks and uses less memory per instance of the class.
     */
    private static class MsgTypeReference extends TypeReference<Msg> {
    }
}

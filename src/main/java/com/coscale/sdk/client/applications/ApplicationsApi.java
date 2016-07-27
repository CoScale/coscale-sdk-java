package com.coscale.sdk.client.applications;

import java.io.IOException;

import com.coscale.sdk.client.ApiClient;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * CoScale API client for Application CoScale endpoint.
 *
 * @author mariomann
 *
 */
public class ApplicationsApi {

    /** ApiClient used to make HTTP requests. */
    private final ApiClient api;

    /**
     * ApplicationsApi constructor.
     *
     * @param api ApiClient.
     */
    public ApplicationsApi(ApiClient api) {
        this.api = api;
    }

    /**
     * Get the current application.
     *
     * @return Application
     * @throws IOException
     */
    public Application getApp() throws IOException {
        return api.callWithAuth("GET", "/", null, new TypeReference<Application>() {});
    }

}
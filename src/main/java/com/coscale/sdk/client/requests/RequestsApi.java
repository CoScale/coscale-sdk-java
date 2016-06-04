package com.coscale.sdk.client.requests;

import java.io.IOException;
import java.util.List;

import com.coscale.sdk.client.ApiClient;
import com.coscale.sdk.client.commons.Options;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * CoScale API client for Requests CoScale endpoint.
 *
 * @author cristi
 *
 */
public class RequestsApi {

    /** ApiClient used to make HTTP requests. */
    private final ApiClient api;

    /**
     * RequestsApi constructor.
     *
     * @param api
     *            ApiClient.
     */
    public RequestsApi(ApiClient api) {
        this.api = api;
    }

    /** Requests end point calls */

    /**
     * all is used to get a list of all requests.
     *
     * @return List<Request>
     * @throws IOException
     */
    public List<Request> all() throws IOException {
        return api.callWithAuth("GET", "/requests/", null, new TypeReference<List<Request>>() {
        });
    }

    /**
     * all is used to get a list of all requests.
     *
     * @param options
     *            which contain query parameters
     * @return List<Request>
     * @throws IOException
     */
    public List<Request> all(Options options) throws IOException {
        String url = "/requests/";
        url += (options.hasQuery() ? "?" : "&") + options.query();
        return api.callWithAuth("GET", url, null, new TypeReference<List<Request>>() {
        });
    }

    /**
     * get a request by the id.
     *
     * @param id
     *            id of the request.
     * @return Request
     * @throws IOException
     */
    public Request get(long id) throws IOException {
        return api.callWithAuth("GET", "/requests/" + id + '/', null, new TypeReference<Request>() {
        });
    }

    /**
     * get a request by the id.
     *
     * @param id
     *            id of the request.
     * @param options
     *            which contain query parameters
     * @return Request
     * @throws IOException
     */
    public Request get(long id, Options options) throws IOException {
        String url = "/requests/" + id + '/';
        url += (options.hasQuery() ? "?" : "&") + options.query();
        return api.callWithAuth("GET", url, null, new TypeReference<Request>() {
        });
    }
}
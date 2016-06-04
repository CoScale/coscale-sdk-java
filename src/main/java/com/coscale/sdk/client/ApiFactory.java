package com.coscale.sdk.client;

import com.coscale.sdk.client.data.DataApi;
import com.coscale.sdk.client.events.EventsApi;
import com.coscale.sdk.client.metrics.MetricsApi;
import com.coscale.sdk.client.requests.RequestsApi;
import com.coscale.sdk.client.servers.ServersApi;

/**
 * ApiFactory is used to instantiate CoScale API Clients.
 *
 * @author cristi
 *
 */
public class ApiFactory {

    /** ApiClient used to make HTTP requests. */
    private final ApiClient api;

    /**
     * ApiFactory Constructor.
     *
     * @param appId
     *            the CoScale application id.
     * @param credentials
     *            the credentials used for login.
     */
    public ApiFactory(String appId, Credentials credentials) {
        this.api = new ApiClient(appId, credentials);
    }

    /**
     * Get the Api Client.
     *
     * @return ApiClient.
     */
    public ApiClient getApiClient() {
        return this.api;
    }

    /**
     * Get a instance of MetricsApi.
     *
     * @return MetricsApi.
     */
    public MetricsApi getMetricsApi() {
        return new MetricsApi(api);
    }

    /**
     * Get a instance of EventsApi.
     *
     * @return EventsApi.
     */
    public EventsApi getEventsApi() {
        return new EventsApi(api);
    }

    /**
     * Get a instance of ServersApi.
     *
     * @return ServersApi.
     */
    public ServersApi getServersApi() {
        return new ServersApi(api);
    }

    /**
     * Get a instance of DataApi.
     *
     * @return DataApi.
     */
    public DataApi getDataApi() {
        return new DataApi(api);
    }
    
    /**
     * Get a instance of RequestsApi.
     *
     * @return RequestsApi.
     */
    public RequestsApi getRequestsApi() {
        return new RequestsApi(api);
    }
}

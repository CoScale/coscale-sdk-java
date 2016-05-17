package com.coscale.sdk.client.servers;

import java.io.IOException;
import java.util.List;

import com.coscale.sdk.client.ApiClient;
import com.coscale.sdk.client.commons.Msg;
import com.coscale.sdk.client.commons.Options;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * ServersApi is used to make API requests for servers endpoints.
 * 
 * @author cristi
 *
 */
public class ServersApi {

    /** ApiClient used to make HTTP requests. */
    private final ApiClient api;

    /**
     * ServersApi constructor.
     * 
     * @param api
     *            ApiClient.
     */
    public ServersApi(ApiClient api) {
        this.api = api;
    }

    /**
     * Get all servers.
     * 
     * @return List of all Servers.
     * @throws IOException
     */
    public List<Server> all() throws IOException {
        return api.callWithAuth("GET", "/servers/", null, new TypeReference<List<Server>>() {
        });
    }

    /**
     * Get all servers.
     * 
     * @param options
     *            which contain query parameters
     * @return List of all Servers.
     * @throws IOException
     */
    public List<Server> all(Options options) throws IOException {
        String url = "/servers/";
        boolean first = true;
        url += (first ? "?" : "&") + options.query();
        first = false;
        return api.callWithAuth("GET", url, null, new TypeReference<List<Server>>() {
        });
    }

    /**
     * Get the a specific server by his id.
     * 
     * @param serverId
     *            the id of the server.
     * @return Server.
     * @throws IOException
     */
    public Server getServer(long serverId) throws IOException {
        return api.callWithAuth("GET", "/servers/" + serverId + "/", null,
                new TypeReference<Server>() {
                });
    }

    /**
     * Get the a specific server by his id.
     * 
     * @param serverId
     *            the id of the server.
     * @param options
     *            which contain query parameters
     * @return
     * @throws IOException
     */
    public Server getServer(long serverId, Options options) throws IOException {
        String url = "/servers/" + serverId + "/";
        boolean first = true;
        url += (first ? "?" : "&") + options.query();
        first = false;
        return api.callWithAuth("GET", url, null, new TypeReference<Server>() {
        });
    }

    /**
     * Insert a new server. Optionally set which other server will be its
     * parent.
     * 
     * @param serverInsert
     *            the server to be added.
     * @return Server.
     * @throws IOException
     */
    public Server insert(ServerInsert serverInsert) throws IOException {
        return api.callWithAuth("POST", "/servers/", serverInsert, new TypeReference<Server>() {
        });
    }

    /**
     * Delete a specific server.
     * 
     * @param serverId
     *            the id of the server to be deleted.
     * @return Msg.
     * @throws IOException
     */
    public Msg delete(long serverId) throws IOException {
        return api.callWithAuth("DELETE", "/servers/" + serverId + "/", null,
                new TypeReference<Msg>() {
                });
    }
}

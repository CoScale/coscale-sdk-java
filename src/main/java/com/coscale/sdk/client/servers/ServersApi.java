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
        url += (options.hasQuery() ? "?" : "&") + options.query();
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
        return api.callWithAuth("GET", "/servers/" + serverId + '/', null,
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
     * @return Server.
     * @throws IOException
     */
    public Server getServer(long serverId, Options options) throws IOException {
        String url = "/servers/" + serverId + '/';
        url += (options.hasQuery() ? "?" : "&") + options.query();
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
        return api.callWithAuth("DELETE", "/servers/" + serverId + '/', null,
                new TypeReference<Msg>() {
                });
    }

    /** Server groups Calls */

    /**
     * Get all server groups.
     * 
     * @return List of all ServerGroups
     * @throws IOException
     */
    public List<ServerGroup> getAllServerGroups() throws IOException {
        return api.callWithAuth("GET", "/servergroups/", null,
                new TypeReference<List<ServerGroup>>() {
                });
    }

    /**
     * Get all server groups.
     * 
     * @param options
     *            filter the ServerGroups. e.g. filter by server name.
     * 
     * @return List of all ServerGroups
     * @throws IOException
     */
    public List<ServerGroup> getAllServerGroups(Options options) throws IOException {
        String url = "/servergroups/";
        url += (options.hasQuery() ? "?" : "&") + options.query();
        return api.callWithAuth("GET", url, null, new TypeReference<List<ServerGroup>>() {
        });
    }

    /**
     * Get a specific server group.
     * 
     * @param id
     *            the id of the server group.
     * @return ServerGroup
     * @throws IOException
     */
    public ServerGroup getServerGroup(long id) throws IOException {
        return api.callWithAuth("GET", "/servergroups/" + id + '/', null,
                new TypeReference<ServerGroup>() {
                });
    }

    /**
     * Insert a new server group. Optionally set which ServerGroup will be its
     * parent (this is set in ServerGroupInsert object).
     * 
     * @param serverGroup
     *            the server group to insert.
     * @return ServerGroup
     * @throws IOException
     */
    public ServerGroup insertServerGroup(ServerGroupInsert serverGroup) throws IOException {
        return api.callWithAuth("POST", "/servergroups/", serverGroup,
                new TypeReference<ServerGroup>() {
                });
    }

    /**
     * Delete a specific server group.
     * 
     * @param id
     *            the id of the server group.
     * @return Msg
     * @throws IOException
     */
    public Msg deleteServerGroup(long id) throws IOException {
        return api.callWithAuth("DELETE", "/servergroups/" + id + '/', null,
                new TypeReference<Msg>() {
                });
    }

    /**
     * Add an existing server group to another server group.
     * 
     * @param childId
     * @param parentId
     * @return List of groups contained by the parent group.
     * @throws IOException
     */
    public List<ServerGroup> addGroupToGroup(long childId, long parentId) throws IOException {
        return api.callWithAuth("POST", "/servergroups/" + parentId + "/servergroups/" + childId
                + '/', null, new TypeReference<List<ServerGroup>>() {
        });
    }

    /**
     * Add an existing server to a server group.
     * 
     * @param serverId
     * @param groupId
     * @return List of Server contained by the group.
     * @throws IOException
     */
    public List<Server> addServerToGroup(long serverId, long groupId) throws IOException {
        return api.callWithAuth("POST", "/servergroups/" + groupId + "/servers/" + serverId + '/',
                null, new TypeReference<List<Server>>() {
                });
    }

    /**
     * * Get all servers in the server group.
     * 
     * @param groupId
     *            the id of the server group.
     * @return List of Server.
     * @throws IOException
     */
    public List<Server> getGroupServers(long groupId) throws IOException {
        return api.callWithAuth("GET", "/servergroups/" + groupId + "/servers/", null,
                new TypeReference<List<Server>>() {
                });
    }

    /**
     * Delete a server from a server group.
     * 
     * @param serverId
     *            the id of the server.
     * @param groupId
     *            the id of the group.
     * @return Msg.
     * @throws IOException
     */
    public Msg deleteServerFromGroup(long serverId, long groupId) throws IOException {
        return api.callWithAuth("DELETE",
                "/servergroups/" + groupId + "/servers/" + serverId + '/', null,
                new TypeReference<Msg>() {
                });
    }

    /**
     * Get all child server groups for a server group.
     * 
     * @param groupId
     *            the if of the group.
     * @return List of ServerGroup.
     * @throws IOException
     */
    public List<ServerGroup> getGroupsFromGroup(long groupId) throws IOException {
        return api.callWithAuth("GET", "/servergroups/" + groupId + "/servergroups/", null,
                new TypeReference<List<ServerGroup>>() {
                });
    }

    /**
     * Delete a server group from another server group.
     * 
     * @param childId
     *            the id of the group that will be removed.
     * @param parentId
     *            the id of the group from which the child group will be
     *            removed.
     * @return Msg.
     * @throws IOException
     */
    public Msg deleteGroupFromGroup(long childId, long parentId) throws IOException {
        return api.callWithAuth("DELETE", "/servergroups/" + parentId + "/servergroups/" + childId
                + '/', null, new TypeReference<Msg>() {
        });
    }
}

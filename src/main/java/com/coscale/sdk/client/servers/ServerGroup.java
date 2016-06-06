package com.coscale.sdk.client.servers;

import java.util.List;

import javax.annotation.Nullable;

import com.coscale.sdk.client.metrics.State;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;

public class ServerGroup {

    @Nullable
    public Long id;

    @Nullable
    public String source;

    @Nullable
    public List<Server> servers;

    @Nullable
    public String description;

    @Nullable
    public String name;

    public State state;

    @Nullable
    public List<ServerGroup> servergroups;

    @Nullable
    public String type;

    @Nullable
    public Long version;

    public ServerGroup(State state) {
        this.state = state;
    }

    public ServerGroup(Long id, String source, List<Server> servers, String description,
            String name, State state, List<ServerGroup> servergroups, String type, Long version) {
        this.id = id;
        this.source = source;
        this.servers = Lists.newArrayList(servers);
        this.description = description;
        this.name = name;
        this.state = state;
        this.servergroups = Lists.newArrayList(servergroups);
        this.type = type;
        this.version = version;
    }

    public ServerGroup() {
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("id", id).add("source", source)
                .add("servers", servers).add("description", description).add("name", name)
                .add("state", state).add("servergroups", servergroups).add("version", version)
                .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ServerGroup other = (ServerGroup) obj;

        return Objects.equal(this.id, other.id) && Objects.equal(this.source, other.source)
                && Objects.equal(this.servers, other.servers)
                && Objects.equal(this.description, other.description)
                && Objects.equal(this.name, other.name) && Objects.equal(this.state, other.state)
                && Objects.equal(this.servergroups, other.servergroups)
                && Objects.equal(this.type, other.type)
                && Objects.equal(this.version, other.version);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, source, servers, description, name, state, servergroups, type,
                version);
    }

}
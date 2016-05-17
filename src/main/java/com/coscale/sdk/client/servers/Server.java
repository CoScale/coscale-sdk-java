package com.coscale.sdk.client.servers;

import javax.annotation.Nullable;

import com.coscale.sdk.client.metrics.Metric;
import com.coscale.sdk.client.metrics.State;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class Server {

    @Nullable
    public Long id;

    @Nullable
    public String source;

    @Nullable
    public java.util.List<Metric> metrics;

    @Nullable
    public String description;

    @Nullable
    public String name;

    public State state;

    @Nullable
    public java.util.List<Server> children;

    @Nullable
    public String type;

    @Nullable
    public Long version;

    public Server(State state) {
        this.state = state;
    }

    public Server(Long id, String source, java.util.List<Metric> metrics, String description,
            String name, State state, java.util.List<Server> children, String type, Long version) {
        this.id = id;
        this.source = source;
        this.metrics = metrics;
        this.description = description;
        this.name = name;
        this.state = state;
        this.children = children;
        this.type = type;
        this.version = version;
    }

    public Server() {
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("id", id).add("source", source)
                .add("metrics", metrics).add("description", description).add("name", name)
                .add("state", state).add("children", children).add("type", type)
                .add("version", version).toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Server other = (Server) obj;

        return Objects.equal(this.id, other.id) && Objects.equal(this.source, other.source)
                && Objects.equal(this.metrics, other.metrics)
                && Objects.equal(this.description, other.description)
                && Objects.equal(this.name, other.name) && Objects.equal(this.state, other.state)
                && Objects.equal(this.children, other.children)
                && Objects.equal(this.type, other.type)
                && Objects.equal(this.version, other.version);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, source, metrics, description, name, state, children, type,
                version);
    }

}
package com.coscale.sdk.client.servers;

import java.util.List;

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
    public List<Metric> metrics;

    @Nullable
    public String description;

    @Nullable
    public String name;

    public State state;

    @Nullable
    public List<Server> children;

    @Nullable
    public String type;

    @Nullable
    public Long version;

    @Nullable
    public List<ServerAttribute> attributes;

    public Server(State state) {
        this.state = state;
    }

    public Server(Long id, String source, List<Metric> metrics, String description, String name,
            State state, List<Server> children, String type, Long version,
            List<ServerAttribute> attributes) {
        this.id = id;
        this.source = source;
        this.metrics = metrics;
        this.description = description;
        this.name = name;
        this.state = state;
        this.children = children;
        this.type = type;
        this.version = version;
        this.attributes = attributes;
    }

    public Server() {
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("id", id).add("source", source)
                .add("metrics", metrics).add("description", description).add("name", name)
                .add("state", state).add("children", children).add("type", type)
                .add("version", version).add("attributes", attributes).toString();
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
                && Objects.equal(this.version, other.version)
                && Objects.equal(this.attributes, other.attributes);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, source, metrics, description, name, state, children, type,
                version, attributes);
    }

}
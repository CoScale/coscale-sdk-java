package com.coscale.sdk.client.requests;

import java.util.List;

import javax.annotation.Nullable;

import com.coscale.sdk.client.commons.Protocol;
import com.coscale.sdk.client.metrics.State;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;

public class Request {

    @Nullable
    public Protocol protocol;

    public State state;

    @Nullable
    public String classifierConfig;

    @Nullable
    public Long version;

    @Nullable
    public Long id;

    @Nullable
    public Long parentId;

    @Nullable
    public RequestClassifierType classifierType;

    @Nullable
    public List<Request> requests;

    @Nullable
    public String source;

    @Nullable
    public String description;

    @Nullable
    public Integer priority;

    @Nullable
    public String name;

    public Request(State state) {
        this.state = state;
    }

    public Request(Protocol protocol, State state, String classifierConfig, Long version, Long id,
            Long parentId, RequestClassifierType classifierType, List<Request> requests,
            String source, String description, Integer priority, String name) {
        this.protocol = protocol;
        this.state = state;
        this.classifierConfig = classifierConfig;
        this.version = version;
        this.id = id;
        this.parentId = parentId;
        this.classifierType = classifierType;
        this.requests = Lists.newArrayList(requests);
        this.source = source;
        this.description = description;
        this.priority = priority;
        this.name = name;
    }

    public Request() {
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("protocol", protocol).add("state", state)
                .add("classifierConfig", classifierConfig).add("version", version).add("id", id)
                .add("parentId", parentId).add("classifierType", classifierType)
                .add("requests", requests).add("source", source).add("description", description)
                .add("priority", priority).add("name", name).toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Request other = (Request) obj;

        return Objects.equal(this.protocol, other.protocol)
                && Objects.equal(this.state, other.state)
                && Objects.equal(this.classifierConfig, other.classifierConfig)
                && Objects.equal(this.version, other.version) && Objects.equal(this.id, other.id)
                && Objects.equal(this.parentId, other.parentId)
                && Objects.equal(this.classifierType, other.classifierType)
                && Objects.equal(this.requests, other.requests)
                && Objects.equal(this.source, other.source)
                && Objects.equal(this.description, other.description)
                && Objects.equal(this.priority, other.priority)
                && Objects.equal(this.name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(protocol, state, classifierConfig, version, id, parentId,
                classifierType, requests, source, description, priority, name);
    }

}
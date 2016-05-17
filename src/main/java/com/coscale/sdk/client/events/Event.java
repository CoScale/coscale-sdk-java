package com.coscale.sdk.client.events;

import javax.annotation.Nullable;

import com.coscale.sdk.client.metrics.State;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class Event {

    @Nullable
    public Long id;

    @Nullable
    public String icon;

    @Nullable
    public String source;

    @Nullable
    public String description;

    @Nullable
    public String name;

    @Nullable
    public Integer eventDataCount;

    public State state;

    @Nullable
    public String type;

    @Nullable
    public String attributeDescriptions;

    @Nullable
    public Long version;

    public Event(State state) {
        this.state = state;
    }

    public Event(Long id, String icon, String source, String description, String name, Integer eventDataCount, State state, String type, String attributeDescriptions, Long version) {
        this.id = id;
        this.icon = icon;
        this.source = source;
        this.description = description;
        this.name = name;
        this.eventDataCount = eventDataCount;
        this.state = state;
        this.type = type;
        this.attributeDescriptions = attributeDescriptions;
        this.version = version;
    }

    public Event() {
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("id", id).add("icon", icon).add("source", source).add("description", description).add("name", name).add("eventDataCount", eventDataCount).add("state", state).add("type", type).add("attributeDescriptions", attributeDescriptions).add("version", version).toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Event other = (Event) obj;

        return Objects.equal(this.id, other.id) && Objects.equal(this.icon, other.icon) && Objects.equal(this.source, other.source) && Objects.equal(this.description, other.description) && Objects.equal(this.name, other.name) && Objects.equal(this.eventDataCount, other.eventDataCount) && Objects.equal(this.state, other.state) && Objects.equal(this.type, other.type) && Objects.equal(this.attributeDescriptions, other.attributeDescriptions) && Objects.equal(this.version, other.version);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, icon, source, description, name, eventDataCount, state, type, attributeDescriptions, version);
    }
}
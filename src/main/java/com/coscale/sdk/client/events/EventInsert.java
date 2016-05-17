package com.coscale.sdk.client.events;

import javax.annotation.Nullable;

import com.coscale.sdk.client.ApiClient;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class EventInsert {

    public String name;

    @Nullable
    public String description;

    @Nullable
    public String attributeDescriptions;

    @Nullable
    public String type;

    @Nullable
    public String icon;

    public String source;

    public EventInsert(String name) {
        this.name = name;
        this.source = ApiClient.getSource();
    }

    public EventInsert(String name, String description, String attributeDescriptions, String type,
            String icon) {
        this.name = name;
        this.description = description;
        this.attributeDescriptions = attributeDescriptions;
        this.type = type;
        this.icon = icon;
        this.source = ApiClient.getSource();
    }

    public EventInsert() {
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("name", name).add("description", description)
                .add("attributeDescriptions", attributeDescriptions).add("type", type)
                .add("icon", icon).add("source", source).toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EventInsert other = (EventInsert) obj;

        return Objects.equal(this.name, other.name)
                && Objects.equal(this.description, other.description)
                && Objects.equal(this.attributeDescriptions, other.attributeDescriptions)
                && Objects.equal(this.type, other.type) && Objects.equal(this.icon, other.icon)
                && Objects.equal(this.source, other.source);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, description, attributeDescriptions, type, icon, source);
    }

}

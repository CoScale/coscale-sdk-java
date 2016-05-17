package com.coscale.sdk.client.servers;

import javax.annotation.Nullable;

import com.coscale.sdk.client.ApiClient;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class ServerInsert {

    public String name;

    public String description;

    public String type;

    public String source;

    @Nullable
    public Long parentId;

    public ServerInsert(String name, String description, String type) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.source = ApiClient.getSource();
    }

    public ServerInsert(String name, String description, String type, Long parentId) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.source = ApiClient.getSource();
        ;
        this.parentId = parentId;
    }

    public ServerInsert() {
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("name", name).add("description", description)
                .add("type", type).add("source", source).add("parentId", parentId).toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ServerInsert other = (ServerInsert) obj;

        return Objects.equal(this.name, other.name)
                && Objects.equal(this.description, other.description)
                && Objects.equal(this.type, other.type) && Objects.equal(this.source, other.source)
                && Objects.equal(this.parentId, other.parentId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, description, type, source, parentId);
    }

}

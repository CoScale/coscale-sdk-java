package com.coscale.sdk.client.servers;

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class ServerAttribute {

    @Nullable
    public Long id;

    @Nullable
    public String source;

    @Nullable
    public String value;

    @Nullable
    public Boolean locked;

    @Nullable
    public String key;

    @Nullable
    public Long version;

    public ServerAttribute(Long id, String source, String value, Boolean locked, String key,
            Long version) {
        this.id = id;
        this.source = source;
        this.value = value;
        this.locked = locked;
        this.key = key;
        this.version = version;
    }

    public ServerAttribute() {
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("id", id).add("source", source)
                .add("value", value).add("locked", locked).add("key", key).add("version", version)
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
        final ServerAttribute other = (ServerAttribute) obj;

        return Objects.equal(this.id, other.id) && Objects.equal(this.source, other.source)
                && Objects.equal(this.value, other.value)
                && Objects.equal(this.locked, other.locked) && Objects.equal(this.key, other.key)
                && Objects.equal(this.version, other.version);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, source, value, locked, key, version);
    }

}
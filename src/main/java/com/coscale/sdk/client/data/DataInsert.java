package com.coscale.sdk.client.data;

import javax.annotation.Nullable;

import com.coscale.sdk.client.ApiClient;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class DataInsert {

    /** The source of the call. This is optional. */
    @Nullable
    public String source;

    /** JSON encoded array of dicts with keys */
    @Nullable
    public String data;

    /**
     * Default Constructor
     */
    public DataInsert() {
        this.source = ApiClient.getSource();
    }

    public DataInsert(String data) {
        this();
        this.data = data;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("source", source).add("data", data).toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DataInsert other = (DataInsert) obj;

        return Objects.equal(this.source, other.source) && Objects.equal(this.data, other.data);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(source, data);
    }
}
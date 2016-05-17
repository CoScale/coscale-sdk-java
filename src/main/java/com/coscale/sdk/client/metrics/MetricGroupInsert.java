package com.coscale.sdk.client.metrics;

import javax.annotation.Nullable;

import com.coscale.sdk.client.ApiClient;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class MetricGroupInsert {

    public String name;

    public String description;

    public String type;

    public SubjectType subject;

    public String source;

    @Nullable
    public Long parentId;

    public MetricGroupInsert(String name, String description, String type, SubjectType subject) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.subject = subject;
        this.source = ApiClient.getSource();
    }

    public MetricGroupInsert(String name, String description, String type, SubjectType subject,
            Long parentId) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.subject = subject;
        this.source = ApiClient.getSource();
        this.parentId = parentId;
    }

    public MetricGroupInsert() {
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("name", name).add("description", description)
                .add("type", type).add("subject", subject).add("source", source)
                .add("parentId", parentId).toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MetricGroupInsert other = (MetricGroupInsert) obj;

        return Objects.equal(this.name, other.name)
                && Objects.equal(this.description, other.description)
                && Objects.equal(this.type, other.type)
                && Objects.equal(this.subject, other.subject)
                && Objects.equal(this.source, other.source)
                && Objects.equal(this.parentId, other.parentId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, description, type, subject, source, parentId);
    }

}
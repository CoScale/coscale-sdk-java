package com.coscale.sdk.client.metrics;

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class Metric {

    @Nullable
    public Long id;

    @Nullable
    public String unit;

    @Nullable
    public DataType dataType;

    @Nullable
    public String source;

    @Nullable
    public SubjectType subject;

    @Nullable
    public String description;

    @Nullable
    public String name;

    @Nullable
    public State state;

    @Nullable
    public Integer period;

    @Nullable
    public Long version;

    public Metric(Long id, String unit, DataType dataType, String source, SubjectType subject,
            String description, String name, State state, Integer period, Long version) {
        this.id = id;
        this.unit = unit;
        this.dataType = dataType;
        this.source = source;
        this.subject = subject;
        this.description = description;
        this.name = name;
        this.state = state;
        this.period = period;
        this.version = version;
    }

    public Metric() {
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("id", id).add("unit", unit)
                .add("dataType", dataType).add("source", source).add("subject", subject)
                .add("description", description).add("name", name).add("state", state)
                .add("period", period).add("version", version).toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Metric other = (Metric) obj;

        return Objects.equal(this.id, other.id) && Objects.equal(this.unit, other.unit)
                && Objects.equal(this.dataType, other.dataType)
                && Objects.equal(this.source, other.source)
                && Objects.equal(this.subject, other.subject)
                && Objects.equal(this.description, other.description)
                && Objects.equal(this.name, other.name) && Objects.equal(this.state, other.state)
                && Objects.equal(this.period, other.period)
                && Objects.equal(this.version, other.version);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, unit, dataType, source, subject, description, name, state,
                period, version);
    }
}
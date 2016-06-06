package com.coscale.sdk.client.metrics;

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;

public class MetricGroup {

    @Nullable
    public Long id;

    @Nullable
    public String source;

    @Nullable
    public java.util.List<Metric> metrics;

    @Nullable
    public SubjectType subject;

    @Nullable
    public String description;

    @Nullable
    public String name;

    public State state;

    @Nullable
    public java.util.List<MetricGroup> metricgroups;

    @Nullable
    public java.util.List<Long> metricIds;

    @Nullable
    public String type;

    @Nullable
    public Long version;

    public MetricGroup(State state) {
        this.state = state;
    }

    public MetricGroup(Long id, String source, java.util.List<Metric> metrics, SubjectType subject,
            String description, String name, State state, java.util.List<MetricGroup> metricgroups,
            java.util.List<Long> metricIds, String type, Long version) {
        this.id = id;
        this.source = source;
        this.metrics = Lists.newArrayList(metrics); // Copy the contents, don't keep reference.
        this.subject = subject;
        this.description = description;
        this.name = name;
        this.state = state;
        this.metricgroups = Lists.newArrayList(metricgroups);
        this.metricIds = Lists.newArrayList(metricIds);
        this.type = type;
        this.version = version;
    }

    public MetricGroup() {
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("id", id).add("source", source)
                .add("metrics", metrics).add("subject", subject).add("description", description)
                .add("name", name).add("state", state).add("metricgroups", metricgroups)
                .add("metricIds", metricIds).add("type", type).add("version", version).toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MetricGroup other = (MetricGroup) obj;

        return Objects.equal(this.id, other.id) && Objects.equal(this.source, other.source)
                && Objects.equal(this.metrics, other.metrics)
                && Objects.equal(this.subject, other.subject)
                && Objects.equal(this.description, other.description)
                && Objects.equal(this.name, other.name) && Objects.equal(this.state, other.state)
                && Objects.equal(this.metricgroups, other.metricgroups)
                && Objects.equal(this.metricIds, other.metricIds)
                && Objects.equal(this.type, other.type)
                && Objects.equal(this.version, other.version);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, source, metrics, subject, description, name, state,
                metricgroups, metricIds, type, version);
    }

}
package com.coscale.sdk.client.metrics;

import com.coscale.sdk.client.ApiClient;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class MetricInsert {

    public String name;

    public String description;

    public DataType dataType;

    public SubjectType subject;

    public String unit;

    public Integer period;

    public String source;

    public MetricInsert(String name, String description, DataType dataType, SubjectType subject,
            String unit, Integer period) {
        this.name = name;
        this.description = description;
        this.dataType = dataType;
        this.subject = subject;
        this.unit = unit;
        this.period = period;
        this.source = ApiClient.getSource();
    }

    public MetricInsert() {
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("name", name).add("description", description)
                .add("dataType", dataType).add("subject", subject).add("unit", unit)
                .add("period", period).add("source", source).toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MetricInsert other = (MetricInsert) obj;

        return Objects.equal(this.name, other.name)
                && Objects.equal(this.description, other.description)
                && Objects.equal(this.dataType, other.dataType)
                && Objects.equal(this.subject, other.subject)
                && Objects.equal(this.unit, other.unit) && Objects.equal(this.period, other.period)
                && Objects.equal(this.source, other.source);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, description, dataType, subject, unit, period, source);
    }

}
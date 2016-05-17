package com.coscale.sdk.client.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class MetricData<T extends MetricData.DataType> {

    @JsonFormat(shape = JsonFormat.Shape.ARRAY)
    static class DataType {

        /** timestamp for the data. */
        public Long timestamp;

        /**
         * DataType constructor.
         * 
         * @param timestamp
         */
        DataType(Long timestamp) {
            this.timestamp = timestamp;
        }

    }

    /** The id of the metric. */
    @JsonProperty("m")
    public Long metricId;

    /** List of Data for this metric id. */
    @JsonProperty("d")
    public List<T> data;

    /** We need to keep the generic type. */
    @JsonIgnore
    private final Class<?> type;

    public MetricData(Long metricId, T data) {
        this.metricId = metricId;
        this.data = new ArrayList<T>(Collections.singletonList(data));

        this.type = data.getClass();
    }

    public void addData(T data) {
        this.data.add(data);
    }

    @JsonIgnore
    public Class<?> getGenericType() {
        return this.type;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("metricId", metricId).add("data", data)
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
        final MetricData<?> other = (MetricData<?>) obj;

        return Objects.equal(this.metricId, other.metricId) && Objects.equal(this.data, other.data);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(metricId, data);
    }

}

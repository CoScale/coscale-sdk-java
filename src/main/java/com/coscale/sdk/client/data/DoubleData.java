package com.coscale.sdk.client.data;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * DoubleData is used to add values for metrics that have DOUBLE type.
 * 
 * @author cristi
 *
 */

public class DoubleData extends MetricData.DataType {

    /** The DoubleData value. */
    public Double value;

    /**
     * DoubleData constructor.
     * 
     * @param timestamp
     *            0 is the current timestamp.
     * 
     *            negative values represent seconds ago.
     * 
     *            positive values represent unix timestamp.
     * 
     * @param value
     *            the Double value for the metric.
     */
    public DoubleData(Long timestamp, Double value) {
        super(timestamp);
        this.value = value;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("timestamp", timestamp).add("value", value)
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
        final DoubleData other = (DoubleData) obj;

        return Objects.equal(this.timestamp, other.timestamp)
                && Objects.equal(this.value, other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(timestamp, value);
    }
}

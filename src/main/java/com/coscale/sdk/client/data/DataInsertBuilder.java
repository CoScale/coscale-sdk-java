package com.coscale.sdk.client.data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.coscale.sdk.client.utils.MapperSupport;

/**
 * DataInsertBuilder will create DataInsert objects used for inserting
 * metrics data. It can be used for the data insert batch call.
 * 
 * @author cristi
 *
 */
public class DataInsertBuilder {

    /** Data will map the DataTypes with the metric ids. */
    private Map<Long, MetricData<? extends MetricData.DataType>> data;

    /**
     * Default Constructor.
     */
    public DataInsertBuilder() {
        this.data = new HashMap<>();
    }

    /**
     * addDoubleData is used to add Data for a metric that has the type DOUBLE.
     * 
     * @param metricId
     *            the id of the metric.
     * @param timestamp
     *            the UNIX timestamp for the value.
     * @param data
     *            the value for the metricId.
     */
    public void addDoubleData(long metricId, long timestamp, double data) {
        MetricData<DoubleData> metricData = getData(DoubleData.class, metricId);
        if (metricData == null) {
            MetricData<DoubleData> md = new MetricData<>(metricId, new DoubleData(timestamp, data));
            this.data.put(metricId, md);
        } else {
            metricData.addData(new DoubleData(timestamp, data));
        }
    }

    /**
     * getData is used to get the MetricData object for the metricId. It will
     * return null if metricId is not found.
     * 
     * @param tClass
     * @param metricId
     * @return
     */
    @SuppressWarnings("unchecked")
    private <T extends MetricData.DataType> MetricData<T> getData(Class<T> tClass, long metricId) {
        if (!data.containsKey(metricId)) {
            return null;
        }

        MetricData<?> md = data.get(metricId);
        if (md.getGenericType() == tClass) {
            return (MetricData<T>) md;
        }

        return null;
    }

    /**
     * build will return a new DataInsert object, containing the serialized data
     * for DataStore Insert API Call.
     * 
     * @return DataInsert object.
     * @throws IOException
     */
    public DataInsert build() {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        try {
            MapperSupport.getInstance().writeValue(b, this.data.values());
        } catch (IOException e) {
            return null;
        }
        return new DataInsert(b.toString());
    }
}

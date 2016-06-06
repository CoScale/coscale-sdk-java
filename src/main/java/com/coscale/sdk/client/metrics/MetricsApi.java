package com.coscale.sdk.client.metrics;

import java.io.IOException;
import java.util.List;

import com.coscale.sdk.client.ApiClient;
import com.coscale.sdk.client.commons.Msg;
import com.coscale.sdk.client.commons.Options;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * MetricsApi is used to make API requests for metrics and metricgroups
 * endpoints.
 * 
 * @author cristi
 *
 */
public class MetricsApi {

    /** ApiClient used to make HTTP requests. */
    private final ApiClient api;

    /**
     * MetricsApi constructor.
     * 
     * @param api
     *            ApiClient.
     */
    public MetricsApi(ApiClient api) {
        this.api = api;
    }

    /**
     * all is used to get a list of all metrics.
     * 
     * @return List<Metric>
     * @throws IOException
     */
    public List<Metric> all() throws IOException {
        return api.callWithAuth("GET", "/metrics/", null, new MetricListTypeReference());
    }

    /**
     * all is used to get a list of all metrics.
     * 
     * @param options
     *            filter the Metrics. e.g. filter by metric name.
     * @return List<Metric>
     * @throws IOException
     */
    public List<Metric> all(Options options) throws IOException {
        String url = "/metrics/";
        url += (options.hasQuery() ? "?" : "&") + options.query();
        return api.callWithAuth("GET", url, null, new MetricListTypeReference());
    }

    /**
     * get a metric by the id.
     * 
     * @param id
     *            id of the metric.
     * @return Metric
     * @throws IOException
     */
    public Metric get(long id) throws IOException {
        return api.callWithAuth("GET", "/metrics/" + id + '/', null, new MetricTypeReference());
    }

    /**
     * Insert a new metric.
     * 
     * @param metric
     *            MetricInsert containing data for the new metric.
     * @return MetricInsert the metric object.
     * @throws IOException
     */
    public Metric insert(MetricInsert metric) throws IOException {
        return api.callWithAuth("POST", "/metrics/", metric, new MetricTypeReference());
    }

    /**
     * Delete a metric by id.
     * 
     * @param id
     *            id of the metric.
     * @return Msg the response message.
     * @throws IOException
     */
    public Msg delete(long id) throws IOException {
        return api.callWithAuth("DELETE", "/metrics/" + id + '/', null, new MsgTypeReference());
    }

    /** Metric groups Calls */

    /**
     * Get all metric groups.
     * 
     * @return List of all MetricGroups
     * @throws IOException
     */
    public List<MetricGroup> getAllMetricGroups() throws IOException {
        return api.callWithAuth("GET", "/metricgroups/", null,
                new MetricGroupListTypeReference());
    }

    /**
     * Get all metric groups.
     * 
     * @param options
     *            filter the MetricGroups. e.g. filter by metric name.
     * 
     * @return List of all MetricGroups
     * @throws IOException
     */
    public List<MetricGroup> getAllMetricGroups(Options options) throws IOException {
        String url = "/metricgroups/";
        url += (options.hasQuery() ? "?" : "&") + options.query();
        return api.callWithAuth("GET", url, null, new MetricGroupListTypeReference());
    }

    /**
     * Get a specific metric group.
     * 
     * @param id
     *            the id of the metric group.
     * @return MetricGroup
     * @throws IOException
     */
    public MetricGroup getMetricGroup(long id) throws IOException {
        return api.callWithAuth("GET", "/metricgroups/" + id + '/', null,
                new MetricGroupTypeReference());
    }

    /**
     * Insert a new metric group. Optionally set which MetricGroup will be its
     * parent (this is set in MetricGroupInsert object).
     * 
     * @param metricGroup
     *            the metric group to insert.
     * @return MetricGroup
     * @throws IOException
     */
    public MetricGroup insertMetricGroup(MetricGroupInsert metricGroup) throws IOException {
        return api.callWithAuth("POST", "/metricgroups/", metricGroup,
                new MetricGroupTypeReference());
    }

    /**
     * Delete a specific metric group.
     * 
     * @param id
     *            the id of the metric group.
     * @return Msg
     * @throws IOException
     */
    public Msg deleteMetricGroup(long id) throws IOException {
        return api.callWithAuth("DELETE", "/metricgroups/" + id + '/', null,
                new MsgTypeReference());
    }

    /**
     * Add an existing metric group to another metric group.
     * 
     * @param childId
     * @param parentId
     * @return List of groups contained by the parent group.
     * @throws IOException
     */
    public List<MetricGroup> addGroupToGroup(long childId, long parentId) throws IOException {
        return api.callWithAuth("POST", "/metricgroups/" + parentId + "/metricgroups/" + childId
                + '/', null, new MetricGroupListTypeReference());
    }

    /**
     * Add an existing metric to a metric group.
     * 
     * @param metricId
     * @param groupId
     * @return List of Metrics contained by the group.
     * @throws IOException
     */
    public List<Metric> addMetricToGroup(long metricId, long groupId) throws IOException {
        return api.callWithAuth("POST", "/metricgroups/" + groupId + "/metrics/" + metricId + '/',
                null, new MetricListTypeReference());
    }

    /**
     * * Get all metrics in the metric group.
     * 
     * @param groupId
     *            the id of the metric group * @return List of Metrics.
     * @return List of Metrics.
     * @throws IOException
     */
    public List<Metric> getGroupMetrics(long groupId) throws IOException {
        return api.callWithAuth("GET", "/metricgroups/" + groupId + "/metrics/", null,
                new MetricListTypeReference());
    }

    /**
     * Delete a metric from a metric group.
     * 
     * @param metricId
     *            the id of the metric.
     * @param groupId
     *            the id of the group.
     * @return Msg.
     * @throws IOException
     */
    public Msg deleteMetricFromGroup(long metricId, long groupId) throws IOException {
        return api.callWithAuth("DELETE",
                "/metricgroups/" + groupId + "/metrics/" + metricId + '/', null,
                new MsgTypeReference());
    }

    /**
     * Get all child metric groups for a metric group.
     * 
     * @param groupId
     *            the if of the group.
     * @return List of MetricGroups.
     * @throws IOException
     */
    public List<MetricGroup> getGroupsFromGroup(long groupId) throws IOException {
        return api.callWithAuth("GET", "/metricgroups/" + groupId + "/metricgroups/", null,
                new MetricGroupListTypeReference());
    }

    /**
     * Delete a metric group from another metric group.
     * 
     * @param childId
     *            the id of the group that will be removed.
     * @param parentId
     *            the id of the group from which the child group will be
     *            removed.
     * @return Msg.
     * @throws IOException
     */
    public Msg deleteGroupFromGroup(long childId, long parentId) throws IOException {
        return api.callWithAuth("DELETE", "/metricgroups/" + parentId + "/metricgroups/" + childId
                + '/', null, new MsgTypeReference());
    }

    /**
     * Copied from the Intellij code analysis:
     * A static inner class does not keep an implicit reference to its enclosing instance.
     * This prevents a common cause of memory leaks and uses less memory per instance of the class.
     */
    private static class MetricListTypeReference extends TypeReference<List<Metric>> {
    }

    private static class MetricTypeReference extends TypeReference<Metric> {
    }

    private static class MsgTypeReference extends TypeReference<Msg> {
    }

    private static class MetricGroupListTypeReference extends TypeReference<List<MetricGroup>> {
    }

    private static class MetricGroupTypeReference extends TypeReference<MetricGroup> {
    }
}

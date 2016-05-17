package com.coscale.sdk.client.events;

import java.io.IOException;
import java.util.List;

import javax.annotation.Nullable;

import com.coscale.sdk.client.ApiClient;
import com.coscale.sdk.client.commons.Msg;
import com.coscale.sdk.client.commons.Options;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * CoScale API client used to create Events and inserting event data.
 *
 * @author cristi
 *
 */
public class EventsApi {

    /** ApiClient used to make HTTP requests. */
    private final ApiClient api;

    /**
     * EventsApi constructor.
     *
     * @param api ApiClient.
     */
    public EventsApi(ApiClient api) {
        this.api = api;
    }

    /** Events end point calls */

    /**
     * all is used to get a list of all events.
     *
     * @return List<Event>
     * @throws IOException
     */
    public List<Event> all() throws IOException {
        return api.callWithAuth("GET", "/events/", null, new TypeReference<List<Event>>() {
        });
    }

    /**
     * all is used to get a list of all events.
     *
     * @param options
     *            which contain query parameters
     * @return List<Event>
     * @throws IOException
     */
    public List<Event> all(Options options) throws IOException {
        String url = "/events/";
        url += (options.hasQuery() ? "?" : "&") + options.query();
        return api.callWithAuth("GET", url, null, new TypeReference<List<Event>>() {
        });
    }

    /**
     * get a event by the id.
     *
     * @param id
     *            id of the event.
     * @return Event
     * @throws IOException
     */
    public Event get(long id) throws IOException {
        return api.callWithAuth("GET", "/events/" + id + '/', null, new TypeReference<Event>() {
        });
    }

    /**
     * Insert a new event.
     *
     * @param event
     *            EventInsert containing data for the new event.
     * @return EventInsert the event object.
     * @throws IOException
     */
    public Event insert(EventInsert event) throws IOException {
        return api.callWithAuth("POST", "/events/", event, new TypeReference<Event>() {
        });
    }

    /**
     * Delete a event by id.
     *
     * @param id
     *            id of the event.
     * @return Msg the response message.
     * @throws IOException
     */
    public Msg delete(long id) throws IOException {
        return api.callWithAuth("DELETE", "/events/" + id + '/', null, new TypeReference<Msg>() {
        });
    }

    /** Events data end point calls. */

    /**
     * Get data from start to stop for a given event. If timestamp = 0 : use the
     * current timestamp. If the timestamp is negative: seconds since the
     * current timestamp. The stop time is optional. Entering only a start time
     * will give all events from that moment onwards.
     *
     * @param eventId
     *            the event id for which the data is requested.
     * @param start
     *            start time.
     * @param stop
     *            stop time.
     * @return List<EventData> a list of events data.
     * @throws IOException
     */
    public List<EventData> allData(Long eventId, @Nullable Long start, @Nullable Long stop)
            throws IOException {
        String endpoint = "/events/" + eventId + "/data/";
        boolean first = true;
        if (start != null) {
            endpoint += (first ? "?" : "&") + "start=" + start;
            first = false;
        }
        if (stop != null) {
            endpoint += (first ? "?" : "&") + "stop=" + stop;
            first = false;
        }

        return api.callWithAuth("GET", endpoint, null, new TypeReference<List<EventData>>() {
        });
    }

    /**
     * Get a single Data point by its id.
     *
     * @param eventId
     *            the event id.
     * @param dataId
     *            the event data id.
     * @return EventData
     * @throws IOException
     */
    public EventData getData(Long eventId, Long dataId) throws IOException {
        return api.callWithAuth("GET", "/events/" + eventId + "/data/get/" + dataId + '/', null,
                new TypeReference<EventData>() {
                });
    }

    /**
     * Create new EventData for a given event.
     *
     * @param eventId
     *            the event for which the data will ne inserted.
     * @param data
     *            EventDataInsert the data that will be inserted.
     * @return EventData the inserted data.
     * @throws IOException
     */
    public EventData insertData(Long eventId, EventDataInsert data) throws IOException {
        return api.callWithAuth("POST", "/events/" + eventId + "/data/", data,
                new TypeReference<EventData>() {
                });
    }

    /**
     * Delete EventData.
     *
     * @param eventId
     *            the event id.
     * @param dataId
     *            the event data id.
     * @return Msg response message.
     * @throws IOException
     */
    public Msg deleteData(Long eventId, Long dataId) throws IOException {
        return api.callWithAuth("DELETE", "/events/" + eventId + "/data/" + dataId + '/', null,
                new TypeReference<Msg>() {
                });
    }

}

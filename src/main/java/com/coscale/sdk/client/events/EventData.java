package com.coscale.sdk.client.events;

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class EventData {

    @Nullable
    public String message;

    @Nullable
    public Long timestamp;

    @Nullable
    public Long id;

    @Nullable
    public Long eventId;

    @Nullable
    public Long updateTime;

    @Nullable
    public String subject;

    @Nullable
    public Long applicationId;

    @Nullable
    public String gid;

    @Nullable
    public String attribute;

    @Nullable
    public Long version;

    @Nullable
    public Long stopTime;

    public EventData(String message, Long timestamp, Long id, Long eventId, Long updateTime,
            String subject, Long applicationId, String gid, String attribute, Long version,
            Long stopTime) {
        this.message = message;
        this.timestamp = timestamp;
        this.id = id;
        this.eventId = eventId;
        this.updateTime = updateTime;
        this.subject = subject;
        this.applicationId = applicationId;
        this.gid = gid;
        this.attribute = attribute;
        this.version = version;
        this.stopTime = stopTime;
    }

    public EventData() {
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("message", message).add("timestamp", timestamp)
                .add("id", id).add("eventId", eventId).add("updateTime", updateTime)
                .add("subject", subject).add("applicationId", applicationId).add("gid", gid)
                .add("attribute", attribute).add("version", version).add("stopTime", stopTime)
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
        final EventData other = (EventData) obj;

        return Objects.equal(this.message, other.message)
                && Objects.equal(this.timestamp, other.timestamp)
                && Objects.equal(this.id, other.id) && Objects.equal(this.eventId, other.eventId)
                && Objects.equal(this.updateTime, other.updateTime)
                && Objects.equal(this.subject, other.subject)
                && Objects.equal(this.applicationId, other.applicationId)
                && Objects.equal(this.gid, other.gid)
                && Objects.equal(this.attribute, other.attribute)
                && Objects.equal(this.version, other.version)
                && Objects.equal(this.stopTime, other.stopTime);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(message, timestamp, id, eventId, updateTime, subject,
                applicationId, gid, attribute, version, stopTime);
    }

}
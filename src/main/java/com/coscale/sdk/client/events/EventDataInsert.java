package com.coscale.sdk.client.events;

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class EventDataInsert {

    public String message;

    public Long timestamp;

    @Nullable
    public Long stopTime;

    @Nullable
    public String attribute;

    public String subject;

    public EventDataInsert(String message, Long timestamp, String subject) {
        this.message = message;
        this.timestamp = timestamp;
        this.subject = subject;
    }

    public EventDataInsert(String message, Long timestamp, Long stopTime, String attribute,
            String subject) {
        this.message = message;
        this.timestamp = timestamp;
        this.stopTime = stopTime;
        this.attribute = attribute;
        this.subject = subject;
    }

    public EventDataInsert() {
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("message", message).add("timestamp", timestamp)
                .add("stopTime", stopTime).add("attribute", attribute).add("subject", subject)
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
        final EventDataInsert other = (EventDataInsert) obj;

        return Objects.equal(this.message, other.message)
                && Objects.equal(this.timestamp, other.timestamp)
                && Objects.equal(this.stopTime, other.stopTime)
                && Objects.equal(this.attribute, other.attribute)
                && Objects.equal(this.subject, other.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(message, timestamp, stopTime, attribute, subject);
    }

}
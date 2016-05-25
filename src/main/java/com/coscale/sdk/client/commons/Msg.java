package com.coscale.sdk.client.commons;

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Msg is used to parse request responses that return a Status Message.
 *
 */
public class Msg {

    @Nullable
    public String msg;

    public Msg(String msg) {
        this.msg = msg;
    }

    public Msg() {
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("msg", msg).toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Msg other = (Msg) obj;

        return Objects.equal(this.msg, other.msg);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(msg);
    }

}

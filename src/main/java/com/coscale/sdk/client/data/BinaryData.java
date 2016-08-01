package com.coscale.sdk.client.data;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;

/**
 * For sending binary data to an existing event.
 * @author kdegroot
 */
public class BinaryData {

    /** The binary data. */
    public byte[] bData;

    /** The provided filename. */
    public String filename;

    public BinaryData(byte[] bData, String filename) {
        this.bData = bData;
        this.filename = filename;
    }

    public BinaryData() {
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("bData", new String(Base64.decodeBase64(bData))).add("filename", filename).toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BinaryData other = (BinaryData) obj;

        return (Arrays.equals(this.bData, other.bData) && this.filename.equals(other.filename));
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(bData, filename);
    }
}

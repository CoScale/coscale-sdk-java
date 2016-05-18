package com.coscale.sdk.client.commons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.google.common.base.Joiner;

/**
 * Options is used to create URL query parameters. Is used for filtering results
 * or for expands.
 */
public class Options {
    private final Map<String, String> selectBys;
    private final Set<String> expands;

    public static class Builder {
        private final Map<String, String> selectBys = new HashMap<>();
        private final Set<String> expands = new HashSet<>();

        public Builder selectBy(String field, String value) {
            try {
                selectBys.put(field, URLEncoder.encode(value, "UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                selectBys.put(field, value);
            }
            return this;
        }

        public Builder expand(String field) {
            try {
                expands.add(URLEncoder.encode(field, "UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                expands.add(field);
            }
            return this;
        }

        public Options build() {
            return new Options(this);
        }
    }

    private Options(Builder builder) {
        selectBys = builder.selectBys;
        expands = builder.expands;
    }

    private List<String> selectByList() {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, String> e : selectBys.entrySet()) {
            result.add("selectBy" + e.getKey() + '=' + e.getValue());
        }
        return result;
    }

    private List<String> expandList() {
        List<String> result = new ArrayList<>();
        for (String field : expands) {
            result.add("expand=" + field);
        }
        return result;
    }

    public String query() {
        List<String> parts = selectByList();
        parts.addAll(expandList());
        return Joiner.on('&').join(parts);
    }

    /**
     * If neither the selectBys or expands has entries, there are no options for the query.
     * @return False if neither selectBys or expands has entries/is initialized.
     * True if either one has entries.
     */
    public boolean hasQuery() {
        if ((selectBys == null || selectBys.isEmpty()) && (expands == null || expands.isEmpty())) {
            return false;
        } else {
            return true;
        }
    }
}

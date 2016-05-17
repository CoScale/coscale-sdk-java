package com.coscale.sdk.client.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MapperSupport {

    /**
     * getInstance is used to get the Instance of a configured ObjectMapper.
     * 
     * @return ObjectMapper used for JSON serialization/deserialization.
     */
    public static ObjectMapper getInstance() {
        return Keeper.INSTANCE;
    }

    private static class Keeper {
        private static final ObjectMapper INSTANCE = configure(new ObjectMapper());

        private static ObjectMapper configure(ObjectMapper mapper) {
            mapper.setSerializationInclusion(Include.NON_NULL);
            return mapper;
        }
    }
}

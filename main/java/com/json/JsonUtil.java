package com.json;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;

public class JsonUtil {
    
    public static String getJson(Object o) {
        return getJson(o, false);
    }

    public static String getJson(Object o, boolean prettyPrint) {
        return getJson(o, false, false);
    }

    public static String getJson(Object o, boolean prettyPrint, boolean includeNonNull) {

        ObjectMapper om = new ObjectMapper().registerModule(new JodaModule())
                                            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        if (includeNonNull) {
            om.setSerializationInclusion(Include.NON_NULL);
        }

        ObjectWriter ow = om.writer();//.withDefaultPrettyPrinter();

        if (prettyPrint)
            ow = ow.withDefaultPrettyPrinter();
        try {
            return ow.writeValueAsString(o);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static <T> List<T> getListObjectFromJson(String jsonString, Class<T> entryObject) {
        List<T> obj = null;
        try {
            ObjectMapper mapper = new ObjectMapper().registerModule(new JodaModule());
            obj = mapper.readValue(jsonString,
                                   mapper.getTypeFactory().constructCollectionType(List.class, entryObject));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }
    
    public static <T> T getObjectFromJson(String jsonString, Class<T> classType) {
        //return mapper.convertValue(jsonString, classType);
        T obj = null;
        try {
            //logger.debug("jsonString: " + jsonString + ", classType: " + classType);

            obj = new ObjectMapper().configure(MapperFeature.USE_GETTERS_AS_SETTERS, false)
                                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                                    .registerModule(new JodaModule()).readValue(jsonString, classType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

}

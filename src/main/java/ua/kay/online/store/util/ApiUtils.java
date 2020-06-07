package ua.kay.online.store.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiUtils {

    /**
     * Format an object into a JSON string
     *
     * @param obj
     * @return
     */
    public static String formatJSON(ObjectMapper objectMapper, Object obj) {

        String json = null;
        try {
            json = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
}

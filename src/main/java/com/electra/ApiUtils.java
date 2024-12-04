package com.electra;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class ApiUtils {
    public static JsonNode parseJsonResponse(String response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(response);
    }
}

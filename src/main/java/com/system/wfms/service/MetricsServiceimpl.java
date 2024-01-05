package com.system.wfms.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;

@Service
public class MetricsServiceimpl implements MetricsService {
    public void separateData(String jsonData) {
        JsonElement jsonElement = JsonParser.parseString(jsonData);
        System.out.println("hallo" + jsonElement);

        if (jsonElement.isJsonArray()) {
            handleJsonArray(jsonElement.getAsJsonArray());
        } else if (jsonElement.isJsonObject()) {
            processJsonElement(jsonElement.getAsJsonObject());
        } else {
            System.out.println("Invalid JSON format: " + jsonData);
        }
    }

    private void handleJsonArray(JsonArray jsonArray) {
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonElement arrayElement = jsonArray.get(i);
            if (arrayElement.isJsonObject()) {
                processJsonElement(arrayElement.getAsJsonObject());
            } else {
                System.out.println("Invalid JSON array element: " + arrayElement);
            }
        }
    }

    private void processJsonElement(JsonObject element) {


        JsonObject data = element.getAsJsonObject("data");

        JsonArray ranges = data.getAsJsonArray("ranges");
        for (int i = 0; i < ranges.size(); i++) {
            // Access the current object in the array
            JsonObject currentObject = ranges.get(i).getAsJsonObject();

            JsonObject metrictest = currentObject.getAsJsonObject("metric");
            String CurrentObjectName = metrictest.get("__name__").getAsString();
            System.out.println("keytest" + CurrentObjectName);

            JsonObject metric = ranges.get(0).getAsJsonObject();
            JsonObject metric2 = metric.getAsJsonObject("metric");
            String nameElement = metric2.get("__name__").getAsString();
        }
        JsonObject metric = ranges.get(0).getAsJsonObject();
        JsonObject metric2 = metric.getAsJsonObject("metric");
        String nameElement = metric2.get("__name__").getAsString();
        if (nameElement != null) {


            // Extracting the first word before "/"
            String[] parts = nameElement.split("/");
            String firstWord = parts.length > 0 ? parts[0] : "";

            System.out.println("Metric: " + nameElement);
            System.out.println("First word: " + firstWord);




        } else {
            System.out.println("Invalid or missing '__name__' field in JSON object: " + element);
        }
    }
}



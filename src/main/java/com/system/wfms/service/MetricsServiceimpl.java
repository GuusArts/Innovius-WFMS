package com.system.wfms.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.system.wfms.Metrics.Sensor;
import com.system.wfms.Metrics.Spark;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MetricsServiceimpl implements MetricsService {
    Map<String, List<JsonObject>> metricGroups = new HashMap<>();
    Long id = 0L;
    String Unit_of_Measurement;
    private List<Sensor> sensorList = new ArrayList<>();
    private List<Spark> sparkList = new ArrayList<>();

    public void separateData(String jsonData) {
        JsonElement jsonElement = JsonParser.parseString(jsonData);

        if (jsonElement.isJsonArray()) {
            handleJsonArray(jsonElement.getAsJsonArray());
        } else if (jsonElement.isJsonObject()) {
            selectFirstWord(jsonElement.getAsJsonObject());
        } else {
            System.out.println("Invalid JSON format: " + jsonData);
        }
    }

    private void handleJsonArray(JsonArray jsonArray) {
        for (JsonElement arrayElement : jsonArray) {
            if (arrayElement.isJsonObject()) {
                selectFirstWord(arrayElement.getAsJsonObject());
            } else {
                System.out.println("Invalid JSON array element: " + arrayElement);
            }
        }
    }

    private void selectFirstWord(JsonObject element) {
        JsonObject data = element.getAsJsonObject("data");
        JsonArray ranges = data.getAsJsonArray("ranges");

        for (JsonElement range : ranges) {
            JsonObject currentObject = range.getAsJsonObject();
            JsonObject metricObject = currentObject.getAsJsonObject("metric");
            String metricObjectName = metricObject.get("__name__").getAsString();

            if (metricObjectName != null) {
                String[] parts = metricObjectName.split("/");
                String firstWord = parts.length > 0 ? parts[0] : "";
                metricGroups.computeIfAbsent(firstWord, k -> new ArrayList<>()).add(currentObject);
            } else {
                System.out.println("Invalid or missing '__name__' field in JSON object: " + element);
            }
        }

        for (Map.Entry<String, List<JsonObject>> entry : metricGroups.entrySet()) {
            String sparkName = entry.getKey();
            List<JsonObject> metricObjects = entry.getValue();

            System.out.println("Objects with metric name '" + sparkName + "':");
            for (JsonObject obj : metricObjects) {
                JsonObject blockObject = obj.getAsJsonObject("metric");
                String blockName = blockObject.get("__name__").getAsString();
                System.out.println("test" + blockName);

                if (blockName.contains("Sensor")) {
                    Pattern pattern = Pattern.compile("\\[([^\\]]+)\\]");
                    Matcher matcher = pattern.matcher(blockName);
                    if (matcher.find()) {
                        Unit_of_Measurement = matcher.group(1);
                    } else {
                        System.out.println("No match found");
                    }

                    JsonArray sensorValues = obj.get("values").getAsJsonArray();
                    Sensor sensor = new Sensor(blockName, Unit_of_Measurement, sensorValues);



                    sensorList.add(sensor);



                }
            }

            System.out.println("////////////////////////////////////");

            Spark spark = new Spark(id++, sparkName, sensorList);
            System.out.println("SparkInfo" + spark.getName() + spark.getId());
            for(Sensor sensor1 : spark.getSensors()){System.out.println("Spark sensors" +sensor1.getName() + sensor1.getValue());}
            System.out.println("////////////////////////////////////");
            sparkList.add(spark);
            sensorList.clear();
        }

        GetMetrics(sparkList);
        sparkList.clear();
        metricGroups.clear(); // Clear the metricGroups map after processing
        id = 0L; // Reset the id for the next iteration
    }
    public List<Spark> GetMetrics(List<Spark> sparkList){

        System.out.println(sparkList.size());
        return sparkList;
    }

}

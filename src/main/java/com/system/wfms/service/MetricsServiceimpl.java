package com.system.wfms.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.system.wfms.DAL.SensorDataRepository;
import com.system.wfms.DAL.SensorRepository;
import com.system.wfms.DAL.SparkRepository;
import com.system.wfms.Metrics.Sensor;
import com.system.wfms.Metrics.SensorData;
import com.system.wfms.Metrics.Spark;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MetricsServiceimpl implements MetricsService {
    Map<String, List<JsonObject>> metricGroups = new HashMap<>();
    Long Sparkid = 0L;

    Long Sensorid = 0L;
    Long Dataid = 0L;

    private List<Sensor> sensorList = new ArrayList<>();
    private List<Spark> sparkList = new ArrayList<>();

    private final SparkRepository sparkRepository;
    private final SensorRepository sensorRepository;
    private final SensorDataRepository sensorDataRepository;

    public MetricsServiceimpl(SparkRepository sparkRepository, SensorRepository sensorRepository, SensorDataRepository sensorDataRepository) {
        this.sparkRepository = sparkRepository;
        this.sensorRepository = sensorRepository;
        this.sensorDataRepository = sensorDataRepository;
    }

    public void GetSparkJson(String jsonData) {
        JsonElement jsonElement = JsonParser.parseString(jsonData);

        if (jsonElement.isJsonArray()) {
            handleJsonArray(jsonElement.getAsJsonArray());
        } else if (jsonElement.isJsonObject()) {
            ConvertJsonToSparkMetric(jsonElement.getAsJsonObject());
        } else {
            System.out.println("Invalid JSON format: " + jsonData);
        }
    }

    private void handleJsonArray(JsonArray jsonArray) {
        for (JsonElement arrayElement : jsonArray) {
            if (arrayElement.isJsonObject()) {
                ConvertJsonToSparkMetric(arrayElement.getAsJsonObject());
            } else {
                System.out.println("Invalid JSON array element: " + arrayElement);
            }
        }
    }

    private void ConvertJsonToSparkMetric(JsonObject element) {
        // Extract data and ranges from the JSON element
        JsonObject data = element.getAsJsonObject("data");
        JsonArray ranges = data.getAsJsonArray("ranges");

        // Create a map to store sensors with their corresponding Spark name
        Map<String, List<Sensor>> sensorsBySpark = new HashMap<>();

        // Create a map to store Spark objects by name
        Map<String, Spark> sparksByName = new HashMap<>();

        // Iterate through the ranges in the JSON element
        for (JsonElement range : ranges) {
            // Extract current object and metric object
            JsonObject currentObject = range.getAsJsonObject();
            JsonObject metricObject = currentObject.getAsJsonObject("metric");

            // Extract metric name
            String metricObjectName = metricObject.get("__name__").getAsString();

            // Check if metric name is present
            if (metricObjectName != null) {
                // Split the metric name to get the first word
                String[] parts = metricObjectName.split("/");
                String firstWord = parts.length > 0 ? parts[0] : "";

                // Create a list to store sensors for the current Spark
                List<Sensor> sensorList = new ArrayList<>();

                // Check if the Spark object already exists, or create a new one
                Spark spark = sparksByName.get(firstWord);
                if (spark == null) {
                    spark = new Spark();
                    spark.setSparkID(Sparkid++);
                    spark.setSparkName(firstWord);
                    spark.setSensorID(Sensorid++);
                    sparksByName.put(firstWord, spark);
                }

                // Check if the metric name contains "Sensor"
                if (metricObjectName.contains("Sensor")) {
                    // Extract unit of measurement from the metric name
                    Pattern pattern = Pattern.compile("\\[([^\\]]+)\\]");
                    Matcher matcher = pattern.matcher(metricObjectName);
                    if (matcher.find()) {
                        String unitOfMeasurement = matcher.group(1);

                        // Create a new Sensor object
                        Sensor sensor = new Sensor();
                        sensor.setSensorName(metricObjectName);
                        sensor.setSensorID(Sensorid);
                        sensor.setSpark(spark);
                        sensor.setUnit_of_Measurment(unitOfMeasurement);

                        // Extract timestamp and value from the JSON object
                        JsonArray sensorValues = currentObject.get("values").getAsJsonArray();
                        Long timestamp = sensorValues.get(0).getAsLong();
                        Double value = sensorValues.get(1).getAsDouble();
                        LocalDateTime localDate = LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault());

                        // Create a SensorData object and set its properties
                        SensorData sensorData = new SensorData();
                        sensorData.setDataID(Dataid++);
                        sensorData.setValue(value);
                        sensorData.setSensor(sensor);
                        sensorData.setTimestamp(localDate);
                        sensorData.setSensorID(sensor.getSensorID());

                        // Add the sensor to the list
                        sensorList.add(sensor);
                    } else {
                        System.out.println("No match found for unit of measurement");
                    }
                }

                // Add the list of sensors to the map with the Spark name
                sensorsBySpark.put(firstWord, sensorList);

            } else {
                System.out.println("Invalid or missing '__name__' field in JSON object: " + element);
            }
        }

        // Iterate through the map of sensors and create Spark objects
        for (Map.Entry<String, List<Sensor>> entry : sensorsBySpark.entrySet()) {
            String sparkName = entry.getKey();
            List<Sensor> sensorList = entry.getValue();

            // Create a Spark object
            Spark spark = new Spark();
            spark.setSparkID(Sparkid++);
            spark.setSparkName(sparkName);
            spark.setSensors(sensorList);
            spark.setSensorID(Sensorid++);

            // Add the Spark to the final list or perform any other necessary actions
            sparkList.add(spark);

        }
        for(Spark spark : sparkList) {
            for(Sensor sensor : spark.getSensors())
            System.out.println("Britt"+ sensor.getSensorName());
        }
        // Clear the metricGroups map after processing
        metricGroups.clear();
        Sparkid = 0L; // Reset the id for the next iteration
        Sensorid = 0L;
        Dataid = 0L; // Reset the data id for the next iteration
    }


    public List<Spark> SendMetricsToDB() {


        return null;
    }
}



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
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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
    private Map<String, Sensor> sensorsByName = new HashMap<>();
    private List<Sensor> allSensors = new ArrayList<>();
    private List<SensorData> allSensorsData= new ArrayList<>();

    Integer Sparkid = 1;

    Integer Sensorid = 1;





    @Autowired
    private SparkRepository sparkRepository;
    @Autowired
    private SensorRepository sensorRepository;
    @Autowired
    private SensorDataRepository sensorDataRepository;

    public void GetSparkJson(String jsonData) {


        JsonElement jsonElement = JsonParser.parseString(jsonData);

        if (jsonElement.isJsonArray()) {
            handleJsonArray(jsonElement.getAsJsonArray())                      ;
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
        JsonObject data = element.getAsJsonObject("data");
        JsonArray ranges = data.getAsJsonArray("ranges");

        Map<String, List<Sensor>> sensorsBySpark = new HashMap<>();
        Map<String, Spark> sparksByName = new HashMap<>();
        Pattern pattern = Pattern.compile("\\[([^\\]]+)\\]");

        for (JsonElement range : ranges) {
            JsonObject currentObject = range.getAsJsonObject();
            JsonObject metricObject = currentObject.getAsJsonObject("metric");
            String metricObjectName = metricObject.get("__name__").getAsString();

            if (metricObjectName != null) {
                String[] parts = metricObjectName.split("/");
                String firstWord = parts.length > 0 ? parts[0] : "";

                List<Sensor> sensorList = sensorsBySpark.computeIfAbsent(firstWord, k -> new ArrayList<>());
                Spark spark = sparksByName.computeIfAbsent(firstWord, k -> {
                    Spark newSpark = new Spark();
                    newSpark.setSparkID(Sparkid++);
                    newSpark.setSparkName(firstWord);

                    return newSpark;
                });

                if (metricObjectName.toLowerCase().contains("sensor")) {
                    Matcher matcher = pattern.matcher(metricObjectName);
                    if (matcher.find()) {
                        String unitOfMeasurement = matcher.group(1);

                        List<SensorData> SensorDataList = new ArrayList<>();
                        Sensor sensor = new Sensor();
                        sensor.setSensorName(metricObjectName);
                        sensor.setSensorID(Sensorid++);
                        sensor.setSparkId(spark.getSparkID());
                        sensor.setUnit_of_Measurment(unitOfMeasurement);
                        JsonArray SensorDataObject = currentObject.get("values").getAsJsonArray();
                        for (JsonElement SensorData : SensorDataObject) {
                            JsonArray sensorValues = SensorData.getAsJsonArray();
                            Long timestamp = sensorValues.get(0).getAsLong();
                            Double value = sensorValues.get(1).getAsDouble();
                            LocalDateTime localDate = LocalDateTime.ofInstant(
                                    Instant.ofEpochSecond(timestamp), ZoneId.systemDefault());

                            SensorData sensorData = new SensorData();
                            sensorData.setDataID(timestamp);
                            sensorData.setValue(value);
                            sensorData.setSensorId(sensor.getSensorID());
                            sensorData.setTimestamp(localDate);


                            SensorDataList.add(sensorData);
                            allSensorsData.addAll(SensorDataList);
                            // You may want to do something with sensorData here
                        }

                        sensorList.add(sensor);
                        allSensors.addAll(sensorList);


                    } else {
                        System.out.println("No match found for unit of measurement");
                    }

                }
            } else {
                System.out.println("Invalid or missing '__name__' field in JSON object: " + element);
            }
        }

        List<Spark> sparkList = new ArrayList<>();
        for (Map.Entry<String, Spark> entry : sparksByName.entrySet()) {
            Spark spark = entry.getValue();

            sparkList.add(spark);
        }

        // Process sparkList, clear maps, reset IDs, etc.
        System.out.println("Het aantal spark is:" + sparkList.size());
        SendMetricsToDB(sparkList);
        sensorsBySpark.clear();
        allSensors.clear();
        sparksByName.clear();
        Sparkid = 0; // Reset the id for the next iteration
        Sensorid = 0;

    }

    @Transactional
    public void SendMetricsToDB(List<Spark> sparkList) {
        for (Spark spark : sparkList) {
            System.out.println("De spark is:" + spark.getSparkName() + spark.getSparkID());
            sparkRepository.save(spark);


        }
        for (Sensor sensor : allSensors) {
            System.out.println("De Sensors zijn:" + sensor.getSparkId());
            sensorRepository.save(sensor);
        }
        for (SensorData sensorData : allSensorsData) {

            sensorDataRepository.save(sensorData);
        }

    }

}




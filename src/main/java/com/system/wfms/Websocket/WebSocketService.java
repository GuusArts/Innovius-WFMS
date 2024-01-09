package com.system.wfms.Websocket;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.wfms.service.MetricsService;
import com.system.wfms.service.MetricsServiceimpl;
import com.system.wfms.service.WebSocketChannel;
import com.system.wfms.service.WebSocketChannelimpl;
import jakarta.annotation.PostConstruct;
import jakarta.websocket.*;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WebSocketService {

    private static final String WS_ENDPOINT = "ws://buildbot64.local/history/timeseries/stream";
    private static String COMMAND_MESSAGE_TEMPLATE = "{\"id\":\"test-command\",\"command\":\"ranges\",\"query\":{\"fields\":[\"%s\"],\"duration\":\"1d\"}}";

    private static final String url = "http://buildbot64.local/history/timeseries/fields";

    private static final String postData = "{\"duration\":\"1d\"}";


    private static String lastReceivedMessage;




    @PostConstruct
    public void init() {
        String jsonResponse = sendPostRequest(url, postData);
        if (jsonResponse != null) {
            modifyCommandMessage(jsonResponse);
            connectToWebSocket();
        }
    }

    private String sendPostRequest(String url, String postData) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // Set any headers you need, for example:
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer YOUR_ACCESS_TOKEN");

            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                wr.write(postData.getBytes(StandardCharsets.UTF_8));
            }

            int responseCode = connection.getResponseCode();
            System.out.println("HTTP Response Code: " + responseCode);

            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }


                return response.toString();
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void modifyCommandMessage(String jsonResponse) {
        try {
            // Parse the JSON array from the response
            ObjectMapper objectMapper = new ObjectMapper();
            List<String> fieldsList = objectMapper.readValue(jsonResponse, new TypeReference<List<String>>() {});

            // Enclose each field in double quotation marks
            List<String> quotedFieldsList = fieldsList.stream()
                    .map(field -> "\"" + field + "\"")
                    .collect(Collectors.toList());

            // Convert the list of quoted fields to a comma-separated string
            String fields = String.join(",", quotedFieldsList);

            // Format the COMMAND_MESSAGE_TEMPLATE with the dynamic fields
            COMMAND_MESSAGE_TEMPLATE = String.format(COMMAND_MESSAGE_TEMPLATE, fields.substring(1, fields.length() - 1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void connectToWebSocket() {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.setDefaultMaxTextMessageBufferSize(1024 * 1024);
            container.connectToServer(WebSocketClient.class, new URI(WS_ENDPOINT));
        } catch (DeploymentException | IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    // Instantiate the MetricProcessorClass


    @ClientEndpoint
    public static class WebSocketClient {


        @OnOpen
        public void onOpen(Session session) {
            try {
                session.getBasicRemote().sendText(COMMAND_MESSAGE_TEMPLATE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @OnMessage
        public void onMessage(String message) {

            WebSocketService.lastReceivedMessage = message;
            handleReceivedMessage();
        }

        public void handleReceivedMessage() {
            // You can access the last received message or perform additional processing here



            MetricsService metricsService = new MetricsServiceimpl();
            WebSocketChannel webSocketChannel = new WebSocketChannelimpl();
            metricsService.GetSparkJson(lastReceivedMessage);
            webSocketChannel.RetrieveWebsocketMessage(lastReceivedMessage);


        }

        @OnClose
        public void onClose(Session session, CloseReason closeReason) {
            System.out.println("[close] Connection closed. Reason: " + closeReason.getReasonPhrase());
        }

        @OnError
        public void onError(Session session, Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}

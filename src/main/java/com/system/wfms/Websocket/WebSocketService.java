package com.system.wfms.Websocket;

import jakarta.annotation.PostConstruct;
import jakarta.websocket.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class WebSocketService {

    private static final String WS_ENDPOINT = "ws://buildbot64.local/history/timeseries/stream";
    private static final String COMMAND_MESSAGE_JSON = "{\"duration\":\"1d\"}";
    private static final String POST_URL = "http://buildbot64.local/history/timeseries/fields";

    @PostConstruct
    public void connectToWebSocket() {
        try {
            // Make the POST request
            makePostRequest();

            // Connect to WebSocket
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(WebSocketClient.class, new URI(WS_ENDPOINT));
        } catch (DeploymentException | IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private void makePostRequest() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        HttpEntity<String> requestEntity = new HttpEntity<>(COMMAND_MESSAGE_JSON, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                POST_URL,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        System.out.println("[post-response] Status code: " + responseEntity.getStatusCodeValue());
        System.out.println("[post-response] Response body: " + responseEntity.getBody());
    }

    @ClientEndpoint
    public class WebSocketClient {

        @OnOpen
        public void onOpen(Session session) {
            try {
                session.getBasicRemote().sendText(COMMAND_MESSAGE_JSON);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @OnMessage
        public void onMessage(String jsonMessage) {
            System.out.println("[message] " + jsonMessage);
        }

        @OnClose
        public void onClose(Session session) {
            System.out.println("[close] Connection closed");
        }

        @OnError
        public void onError(Session session, Throwable throwable) {
            System.out.println("[error] " + throwable.getMessage());
        }
    }
}

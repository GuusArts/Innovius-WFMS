package com.system.wfms.Websocket;

import jakarta.annotation.PostConstruct;
import jakarta.websocket.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class WebSocketService {

    private static final String WS_ENDPOINT = "ws://buildbot64.local/history/timeseries/stream";
    private static final String COMMAND_MESSAGE = "{\"id\":\"test-command\",\"command\":\"ranges\",\"query\":{\"fields\":[\"battlebot64/Kettle Sensor/value[degC]\"],\"duration\":\"1d\"}}";

    @PostConstruct
    public void connectToWebSocket() {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.setDefaultMaxTextMessageBufferSize(1024 * 1024); // Set buffer size in bytes (adjust as needed)
            container.connectToServer(WebSocketClient.class, new URI(WS_ENDPOINT));
        } catch (DeploymentException | IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @ClientEndpoint
    public static class WebSocketClient {

        @OnOpen
        public void onOpen(Session session) {
            try {
                session.getBasicRemote().sendText(COMMAND_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @OnMessage
        public void onMessage(String message) {
            System.out.println("[message] " + message);
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

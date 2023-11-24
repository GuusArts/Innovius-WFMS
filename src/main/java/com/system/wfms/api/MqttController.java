package com.system.wfms.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MqttController {

    @Autowired
    @Qualifier("mqttInputChannel")  // Specify the bean name you want to inject
    private MessageChannel mqttOutboundChannel;

    @GetMapping("/publish-message")
    public String publishMessage() {
        String messagePayload = "Hello, MQTT!";
        Message<String> mqttMessage = MessageBuilder.withPayload(messagePayload).build();
        mqttOutboundChannel.send(mqttMessage);
        return "redirect:/";
    }

    @GetMapping("/")
    public String viewMqttData(Model model) {
        // For simplicity, let's assume the MQTT data is stored in a service
        String mqttData = retrieveMqttData();
        model.addAttribute("mqttData", mqttData);
        return "mqtt-view";
    }

    private String retrieveMqttData() {
        // Simulated method to retrieve MQTT data from the server bus
        // Replace this with your actual implementation
        return "Data from MQTT Server Bus";
    }
}

package com.system.wfms.config;

import com.system.wfms.service.WineTankService;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class MqttConfig {

    private final WineTankService wineTankService;



    public MqttConfig(WineTankService wineTankService) {
        this.wineTankService = wineTankService;
    }

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(mqttConnectOptions());
        return factory;
    }

    @Bean
    public MessageProducer inboundLiveData() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter("mqtt-explorer-bf22114c", mqttClientFactory(), "brewcast/history/battlebot64");
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }



    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @ServiceActivator(inputChannel = "mqttInputChannel")
    @Bean
    public MessageHandler mqttMessageHandler() {
        return message -> {
            String payload = (String) message.getPayload();
            System.out.println(payload);
            try {
                wineTankService.processTemperatureSensor(payload);
                wineTankService.processSideKettleSensor(payload);
                wineTankService.ConvertPayloadToWineTank(payload);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    @Bean
    public MqttConnectOptions mqttConnectOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[]{"tcp://buildbot64:1883"});
        options.setPassword("flappie".toCharArray());
        options.setUserName("Guus1245");


        // Correct the scheme to "tcp"

        // Add other configuration if required (e.g., username, password)
        return options;
    }
}

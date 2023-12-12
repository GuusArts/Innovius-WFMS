package com.system.wfms;

import com.system.wfms.Models.TemperatureData;
import com.system.wfms.service.KettleService;
import com.system.wfms.service.MqttModelService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class WfmsApplicationTests {

		@Mock
		private MqttModelService mqttModelService;

		@Mock
		private Logger logger;


		@Mock
		private KettleService kettleService;

		@Test
		void testProcessTemperatureSensor() throws Exception {
			// Mocking ConvertJsonToModel method

			when(kettleService.processTemperatureSensor(anyString())).thenReturn(new TemperatureData(30.8125, new Date(), 1L));

			String payload = "{\n" +
					"  \"key\": \"battlebot64\",\n" +
					"  \"data\": {\n" +
					"    \"SystemInfo\": {\n" +
					"      \"updatesPerSecond\": 161.907,\n" +
					"      \"voltage5\": 4.775,\n" +
					"      \"voltageExternal\": 5.325,\n" +
					"      \"memoryFree\": 97328,\n" +
					"      \"memoryFreeContiguous\": 94192,\n" +
					"      \"memoryFreeLowest\": 75608,\n" +
					"      \"uptime[second]\": 780.061\n" +
					"    },\n" +
					"    \"DisplaySettings\": {},\n" +
					"    \"Kettle\": {},\n" +
					"    \"Kettle Sensor\": {\n" +
					"      \"value[degC]\": 30.4375\n" +
					"    },\n" +
					"    \"Kettle Setpoint\": {\n" +
					"      \"value[degC]\": 30.449462890625,\n" +
					"      \"setting[degC]\": 70.0,\n" +
					"      \"storedSetting[degC]\": 70.0,\n" +
					"      \"valueUnfiltered[degC]\": 30.4375,\n" +
					"      \"desiredSetting[degC]\": 70.0\n" +
					"    },\n" +
					"    \"Kettle Actuator\": {\n" +
					"      \"storedState\": 0,\n" +
					"      \"desiredState\": 0,\n" +
					"      \"state\": null\n" +
					"    },\n" +
					"    \"Kettle PWM\": {\n" +
					"      \"setting\": 100.0,\n" +
					"      \"desiredSetting\": 1981.19384765625,\n" +
					"      \"storedSetting\": 0.0,\n" +
					"      \"value\": null\n" +
					"    },\n" +
					"    \"Kettle PID\": {\n" +
					"      \"outputSetting\": 100.0,\n" +
					"      \"enabled\": true,\n" +
					"      \"active\": true,\n" +
					"      \"p\": 1977.52685546875,\n" +
					"      \"i\": 3.29638671875,\n" +
					"      \"d\": 0.37060546875,\n" +
					"      \"derivative\": 0.037067413330078125,\n" +
					"      \"outputValue\": null,\n" +
					"      \"integral\": 0.0,\n" +
					"      \"integralReset\": 0.0,\n" +
					"      \"boilModeActive\": false,\n" +
					"      \"error[delta_degC]\": 39.550537109375,\n" +
					"      \"inputSetting[degC]\": 70.0,\n" +
					"      \"inputValue[degC]\": 30.449462890625\n" +
					"    },\n" +
					"    \"SideSensor\": {\n" +
					"      \"value[degC]\": 20.1875\n" +
					"    },\n" +
					"    \"MPC Digital Actuator\": {\n" +
					"      \"storedState\": 0,\n" +
					"      \"desiredState\": 0,\n" +
					"      \"state\": 0\n" +
					"    }\n" +
					"  }\n" +
					"}\n"; // replace with an actual payload

			// Execute the method
			TemperatureData result = kettleService.processTemperatureSensor(payload);

			// Verify the expected behavior
			assertEquals(30.4375, result.getTemp());


			// Verify that the logger was called with the correct parameters
			Mockito.verify(logger).info("Water temperature processed successfully: {} {} {}", result.getTemp());
		}
	}



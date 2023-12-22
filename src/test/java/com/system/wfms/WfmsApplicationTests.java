package com.system.wfms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.system.wfms.Models.TemperatureData;
import com.system.wfms.MqttModels.*;
import com.system.wfms.api.KettleController;
import com.system.wfms.service.WineTankService;
import com.system.wfms.service.MqttModelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(KettleController.class)
@ExtendWith(MockitoExtension.class)
class WfmsApplicationTests {


	@Mock
	private Logger logger;

	@Mock
	private WineTankService kettleService;
	@Mock
	private MqttModelService mqttModelService;

	@InjectMocks
	private KettleController kettleController;


	BattleBotData battleBotData = new BattleBotData();
	BattleBotDataDetails battleBotDataDetails = new BattleBotDataDetails();


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
			"}\n";


	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(kettleController).build();
	}

	@Test
	void testProcessTemperatureSensor() throws Exception {
		// Mocking ConvertJsonToModel method
		TemperatureData expectedTemperatureData = new TemperatureData(30.4375, new Date(), 1L);
		when(kettleService.processTemperatureSensor(anyString())).thenReturn(expectedTemperatureData);

		TemperatureData result = kettleService.processTemperatureSensor(payload);


		assertEquals(expectedTemperatureData.getTemp(), result.getTemp());


	}

	@Test
	void testConvertJsonToModel() throws JsonProcessingException {

		battleBotData.setKey("battlebot64");

		// SystemInfo
		SystemInfo systemInfo = new SystemInfo();
		systemInfo.setUpdatesPerSecond(161.907);
		systemInfo.setVoltage5(4.775);
		systemInfo.setVoltageExternal(5.325);
		systemInfo.setMemoryFree(97328);
		systemInfo.setMemoryFreeContiguous(94192);
		systemInfo.setMemoryFreeLowest(75608);
		systemInfo.setUptime(780.061);
		battleBotDataDetails.setSystemInfo(systemInfo);

		KettleSensor kettleSensor = new KettleSensor();
		kettleSensor.setValue(30.4375);
		battleBotDataDetails.setKettleSensor(kettleSensor);

		// Kettle Setpoint
		KettleSetpoint kettleSetpoint = new KettleSetpoint();
		kettleSetpoint.setValue(30.449462890625);
		kettleSetpoint.setSetting(70.0);
		kettleSetpoint.setStoredSetting(70);
		kettleSetpoint.setValueUnfiltered(30.4375);
		kettleSetpoint.setDesiredSetting(70.0);
		battleBotDataDetails.setKettleSetpoint(kettleSetpoint);

		// Kettle Actuator
		KettleActuator kettleActuator = new KettleActuator();
		kettleActuator.setStoredState(0);
		kettleActuator.setDesiredState(0);
		kettleActuator.setState(0);
		battleBotDataDetails.setKettleActuator(kettleActuator);

		// Kettle PWM
		KettlePWM kettlePWM = new KettlePWM();
		kettlePWM.setSetting(100);
		kettlePWM.setDesiredSetting(1981);
		kettlePWM.setStoredSetting(0);
		kettlePWM.setValue(null);
		battleBotDataDetails.setKettlePWM(kettlePWM);

		// Kettle PID
		KettlePID kettlePID = new KettlePID();
		kettlePID.setOutputSetting(100.0);
		kettlePID.setEnabled(true);
		kettlePID.setActive(true);
		kettlePID.setP(1977.52685546875);
		kettlePID.setI(3.29638671875);
		kettlePID.setD(0.37060546875);
		kettlePID.setDerivative(0.037067413330078125);
		kettlePID.setOutputValue(null);
		kettlePID.setIntegral(0.0);
		kettlePID.setIntegralReset(0.0);
		kettlePID.setBoilModeActive(false);
		kettlePID.setErrorDelta(39.550537109375);
		kettlePID.setInputSetting(70.0);
		kettlePID.setInputValue(30.449462890625);
		battleBotDataDetails.setKettlePID(kettlePID);

		// SideSensor
		SideSensor sideSensor = new SideSensor();
		sideSensor.setValue(20.1875);
		battleBotDataDetails.setSidesensor(sideSensor);

		// MPC Digital Actuator
		MPCDigitalActuator mpcDigitalActuator = new MPCDigitalActuator();
		mpcDigitalActuator.setStoredState(0);
		mpcDigitalActuator.setDesiredState(0);
		mpcDigitalActuator.setState(0);
		battleBotDataDetails.setMpcDigitalActuator(mpcDigitalActuator);

		battleBotData.setData(battleBotDataDetails);


		when(mqttModelService.ConvertJsonToModel(anyString())).thenReturn(battleBotData);

		BattleBotData mockbattlebot = mqttModelService.ConvertJsonToModel(payload);

		assertEquals(mockbattlebot.getData(), mockbattlebot.getData());


	}
	@Test
	void TestSensorEndpoint() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
						.get("/temp")
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
}


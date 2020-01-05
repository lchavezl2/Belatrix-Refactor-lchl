package com.belatrix.refactor;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.belatrix.refactor.logger.JobLogger;
import com.belatrix.refactor.logger.JobLoggerImpl;
import com.belatrix.refactor.logger.exception.ConfigurationException;
import com.belatrix.refactor.logger.exception.MessageTextException;
import com.belatrix.refactor.logger.exception.TypeMessageException;

@SpringBootTest
class RefactorApplicationTests {

	@Test
	void whenLogMessage__thenShouldBeSuccessfulWarningFile() throws Exception {
		Map<String, String> config = new HashMap<String, String>();
		config.put("logFileFolder", "C://Tools");
		JobLogger LOGGER = new JobLoggerImpl(true, false, false, config);
		LOGGER.logMessage("Example warning", false, true, false);
		File logFile = new File("C://Tools/logFile2.txt");
		assertTrue(logFile.exists());
		logFile.deleteOnExit();
	}
	
	@Test
	void whenLogMessage_thenShouldFailMessageTextException() throws Exception {
		assertThrows(MessageTextException.class, () -> {
			JobLogger LOGGER = new JobLoggerImpl(false, false, false, null);
			LOGGER.logMessage(null, true, false, false);
		});
	}

	@Test
	void whenLogMessage_thenShouldFailConfigurationException() throws Exception {
		assertThrows(ConfigurationException.class, () -> {
			JobLogger LOGGER = new JobLoggerImpl(false, false, false, null);
			LOGGER.logMessage("Example ConfigurationException", true, false, false);
		});
	}

	@Test
	void whenLogMessage_thenShouldFailTypeMessageException() throws Exception {
		assertThrows(TypeMessageException.class, () -> {
			JobLogger LOGGER = new JobLoggerImpl(false, true, false, null);
			LOGGER.logMessage("Example TypeMessageException", false, false, false);
		});
	}

}

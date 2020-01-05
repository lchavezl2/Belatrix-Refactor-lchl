package com.belatrix.refactor.logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.belatrix.refactor.logger.exception.ConfigurationException;
import com.belatrix.refactor.logger.exception.MessageTextException;
import com.belatrix.refactor.logger.exception.TypeMessageException;
import com.belatrix.refactor.logger.handler.BdOutput;
import com.belatrix.refactor.logger.handler.ConsoleOutput;
import com.belatrix.refactor.logger.handler.FileOutput;

public class JobLoggerImpl implements JobLogger {

	private static boolean logToFile;
	private static boolean logToConsole;
	private static boolean logToDatabase;
	private static Map dbParams;
	private static Logger logger;

	public JobLoggerImpl(boolean logToFileParam, boolean logToConsoleParam, boolean logToDatabaseParam,
			Map dbParamsMap) {
		logger = Logger.getLogger("MyLog");
		logToDatabase = logToDatabaseParam;
		logToFile = logToFileParam;
		logToConsole = logToConsoleParam;
		dbParams = dbParamsMap;
	}

	private void info(String message) {
		logger.log(Level.INFO, message);
	}

	private void warn(String message) {
		logger.log(Level.WARNING, message);
	}

	private void error(String message) {
		logger.log(Level.SEVERE, message);
	}

	@Override
	public void logMessage(String messageText, Boolean info, Boolean warning, Boolean error)
			throws TypeMessageException, ConfigurationException, MessageTextException, SQLException, IOException {

		if (messageText == null || messageText.trim().isEmpty()) {
			throw new MessageTextException("The message must not be null") ;
		}

		if (!logToConsole && !logToFile && !logToDatabase) {
			throw new ConfigurationException("Invalid configuration");
		}
		if ((!info && !warning && !error)) {
			throw new TypeMessageException("Error or Warning or Message must be specified");
		}

		if (logToFile) {
			logger.addHandler(FileOutput.getFileHandler(dbParams));
			printMessage(messageText, info, warning, error);
		}

		if (logToConsole) {
			logger.addHandler(ConsoleOutput.getConsoleHandler());
			printMessage(messageText, info, warning, error);
		}

		if (logToDatabase) {
			int t = getTypeError(info, warning, error);
			BdOutput.executeUpdate(dbParams,
					"insert into Log_Values('" + messageText + "', " + String.valueOf(t) + ")");
		}

	}

	private void printMessage(String messageText, Boolean message, Boolean warning, Boolean error) {
		if (message) {
			info(messageText);
		}

		if (error) {
			error(messageText);
		}

		if (warning) {
			warn(messageText);
		}
	}

	private int getTypeError(Boolean message, Boolean warning, Boolean error) {
		int t = 0;
		if (message) {
			t = com.belatrix.refactor.logger.domain.Level.INFO.getValue();
		}

		if (error) {
			t = com.belatrix.refactor.logger.domain.Level.ERROR.getValue();
		}

		if (warning) {
			t = com.belatrix.refactor.logger.domain.Level.WARN.getValue();
		}

		return t;
	}

}

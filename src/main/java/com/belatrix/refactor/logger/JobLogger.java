package com.belatrix.refactor.logger;

import java.io.IOException;
import java.sql.SQLException;

import com.belatrix.refactor.logger.exception.ConfigurationException;
import com.belatrix.refactor.logger.exception.MessageTextException;
import com.belatrix.refactor.logger.exception.TypeMessageException;

public interface JobLogger {
	void logMessage(String messageText, Boolean info, Boolean warning, Boolean error)
			throws TypeMessageException, ConfigurationException, MessageTextException, SQLException, IOException;
}

package com.belatrix.refactor.logger.handler;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class FileOutput {

	public static FileHandler getFileHandler(Map dbParamsMap) throws IOException {
		try {
			File logFile = new File(dbParamsMap.get("logFileFolder") + "/logFile2.txt");
			if (!logFile.exists()) {
				logFile.createNewFile();
			}
			FileHandler fh = new FileHandler(dbParamsMap.get("logFileFolder") + "/logFile2.txt");
//	        fh.setFormatter(new SimpleFormatter ());
			return fh;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

}

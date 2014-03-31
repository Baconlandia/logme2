package com.agilemobiledeveloper.logme;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

@Service
public class LogDataService {

	 Logger logger = LoggerFactory.getLogger(LogDataService.class);

	@Autowired 
	MongoConfiguration mongoConfiguration;
	
	public void addLogMessage(final LogEntry newLog) {
		if (newLog == null) {
			return ;
		}
		logger.info("Log in ds=" + newLog.toString());
		
		try {
			MongoOperations  mongoOperations = mongoConfiguration.mongoTemplate();
			mongoOperations.insert(newLog);
			
			logger.info("Saved");
		}
		catch(Exception x) {
			logger.error(x.getLocalizedMessage());
		}
	}
}

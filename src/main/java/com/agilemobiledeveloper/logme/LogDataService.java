package com.agilemobiledeveloper.logme;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
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
		//	ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfiguration.class);
			
//			MongoOperations  mongoOperations = (MongoOperations) ctx.getBean("mongoTemplate"); // mongoConfiguration.mongoTemplate();
	
			MongoOperations  mongoOperations = mongoConfiguration.mongoTemplate();
			mongoOperations.insert(newLog);
			
			logger.info("Saved");
		}
		catch(Exception x) {
			x.printStackTrace();
			logger.error(x.getLocalizedMessage());
		}

	}
}

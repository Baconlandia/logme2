/**
 * 
 */
package com.agilemobiledeveloper.logme;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

/**
 * @author Timothy
 *
 */
@Configuration
@EnableMongoRepositories
public class MongoConfiguration extends AbstractMongoConfiguration {
	


	@Bean
	public LogDataService getLogDataService() {
		return new LogDataService();
	}

	
	@Override
	protected String getDatabaseName() {
		return "logs";
	}

	@Override
	@Bean
	public  Mongo mongo() throws Exception {		
		return new MongoClient("localhost");
	}
	
	@Override
	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongo(), getDatabaseName());
	}

}
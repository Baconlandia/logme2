package com.agilemobiledeveloper.logme;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.common.base.Joiner;

@Controller
@Configuration
@ComponentScan
@EnableMongoRepositories
@RestController
@EnableAutoConfiguration
@RequestMapping(value="/")
public class LogController {

	 Logger logger = LoggerFactory.getLogger(LogController.class);
	 
	  public static HttpServletRequest getCurrentRequest() {
	        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
	        Assert.state(requestAttributes != null, "Could not find current request via RequestContextHolder");
	        Assert.isInstanceOf(ServletRequestAttributes.class, requestAttributes);
	        HttpServletRequest servletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
	        Assert.state(servletRequest != null, "Could not find current HttpServletRequest");
	        return servletRequest;
	    }
	  
   @Autowired
	LogDataService logDataService;
	
    @RequestMapping( method=RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public LogResponse log(@RequestBody LogEntry logEntry) {
    	logger.info("Log: " +logEntry.toString());
		
    	final String userIpAddress = getCurrentRequest().getRemoteAddr();
        final String userAgent = getCurrentRequest().getHeader("user-agent");
        
		if (logDataService != null && logEntry != null) {
			Joiner joiner = Joiner.on(": ").skipNulls();
			
			logEntry.setLogMessage(
					joiner.join(userIpAddress, userAgent, logEntry.getLogMessage()));
					
			logDataService.addLogMessage(logEntry);
		}
		else {
			logger.info("value is null");
		}
		return new LogResponse("Logged");
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(LogController.class, args);
    }
}
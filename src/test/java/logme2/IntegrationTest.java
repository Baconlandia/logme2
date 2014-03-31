package logme2;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.agilemobiledeveloper.logme.LogDataService;
import com.agilemobiledeveloper.logme.LogEntry;
import com.agilemobiledeveloper.logme.LogResponse;



@Ignore
public class IntegrationTest {

	Logger logger = LoggerFactory.getLogger(IntegrationTest.class);

	@Ignore
	public  void testLog() {
        RestTemplate restTemplate = new RestTemplate();
        LogEntry logEntry = new LogEntry("test", "Tim Test", "This is a message");
       LogResponse response  = restTemplate.postForObject("http://localhost:8080", logEntry, LogResponse.class, "");

       logger.error("Response {}", response);
       
    }
	
}

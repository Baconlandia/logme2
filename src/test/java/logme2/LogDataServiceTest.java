/**
 * 
 */
package logme2;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.agilemobiledeveloper.logme.LogDataService;
import com.agilemobiledeveloper.logme.LogEntry;
import com.agilemobiledeveloper.logme.MongoConfiguration;

/**
 * @author Timothy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MongoConfiguration.class})
public class LogDataServiceTest {

	@Autowired
	LogDataService logDataService;
	
	/**
	 * Test method for {@link com.agilemobiledeveloper.logme.LogDataService#addLogMessage(com.agilemobiledeveloper.logme.LogEntry)}.
	 */
	@Test
	public final void testAddEmptyLogMessage() {
		LogEntry newLog = null;
		logDataService.addLogMessage(newLog);
	}

	/**
	 * Test method for {@link com.agilemobiledeveloper.logme.LogDataService#addLogMessage(com.agilemobiledeveloper.logme.LogEntry)}.
	 */
	@Test
	public final void testAddLogMessage() {
		LogEntry newLog = new LogEntry("JUnit", "TEST", "TEST2");
		logDataService.addLogMessage(newLog);
	}
}

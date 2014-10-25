import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.integration.samples.helloworld.HelloService;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Robert on 25/10/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/META-INF/spring/integration/helloWorldDemo.xml")
public class HelloWorldTest {

    @Autowired
    HelloService helloService;

    @Autowired
    ApplicationContext applicationContext;

    private static Logger logger = Logger.getLogger(HelloWorldTest.class);

    @Test
    public void shouldPrintHelloWorld()
    {
        MessageChannel inputChannel = applicationContext.getBean("inputChannel", MessageChannel.class);
        PollableChannel outputChannel = applicationContext.getBean("outputChannel", PollableChannel.class);
        inputChannel.send(new GenericMessage<String>("World"));
        Assert.assertEquals("should received 'hello world'", "Hello World", outputChannel.receive(0).getPayload());
    }
}

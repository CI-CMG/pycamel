package edu.colorado.cires.cmg.pycamel;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringRunner;
import org.apache.camel.test.spring.CamelTestContextBootstrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;

@RunWith(CamelSpringRunner.class)
@BootstrapWith(CamelTestContextBootstrapper.class)
@ContextConfiguration("/test-context.xml")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class JythonPythonObjectFactoryTest {

  @Autowired
  private ProducerTemplate producer;

  @EndpointInject("mock:process-out")
  private MockEndpoint processOut;

  @EndpointInject("mock:property-test-out")
  private MockEndpoint propertyTestOut;

  @EndpointInject("mock:property-test-out2")
  private MockEndpoint propertyTestOut2;

  @Test
  public void jythonProcessorExecuteTest() throws Exception {
    processOut.expectedBodiesReceived("{\"foo\": \"bar\"}");
    producer.sendBody("direct:process", "{}");
    processOut.assertIsSatisfied();
  }

  @Test
  public void jythonProcessorSimpleSetPropertiesTest() throws Exception {
    propertyTestOut.expectedBodiesReceived("{\"name\": \"test\"}");
    producer.sendBody("direct:property-test", "{}");
    propertyTestOut.assertIsSatisfied();
  }

  @Test
  public void jythonProcessorComplexSetPropertiesTest() throws Exception {
    propertyTestOut2.expectedBodiesReceived("{\"name\": \"test2\"}");
    producer.sendBody("direct:property-test2", "{}");
    propertyTestOut2.assertIsSatisfied();
  }

}
package edu.colorado.cires.cmg.pycamel;

import static org.junit.Assert.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringRunner;
import org.apache.camel.test.spring.CamelTestContextBootstrapper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;

@RunWith(CamelSpringRunner.class)
@BootstrapWith(CamelTestContextBootstrapper.class)
@ContextConfiguration("/jpy-context.xml")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class JpyPythonObjectFactoryTest {

  @BeforeClass
  public static void beforeClass() throws Exception {

    Path jpyDir = Paths.get("jpy").toAbsolutePath();

    Path libDir;
    try(Stream<Path> paths = Files.list(jpyDir.resolve("build"))) {
      libDir = paths
          .filter(path -> path.getFileName().toString().startsWith("lib."))
          .findFirst()
          .orElseThrow(() -> new RuntimeException("Unable to find jpy lib dir"));
    }

    Path jpyconfigProperties = libDir.resolve("jpyconfig.properties");
    System.setProperty("jpy.config", jpyconfigProperties.toAbsolutePath().toString());
  }

  @Autowired
  private ProducerTemplate producer;

  @EndpointInject("mock:process-out")
  private MockEndpoint processOut;

  @EndpointInject("mock:property-test-out")
  private MockEndpoint propertyTestOut;

  @EndpointInject("mock:property-test-out2")
  private MockEndpoint propertyTestOut2;

  @Test
  public void processorExecuteTest() throws Exception {
    processOut.expectedBodiesReceived("{\"foo\": \"bar\"}");
    producer.sendBody("direct:process", "{}");
    processOut.assertIsSatisfied();
  }

  @Test
  public void processorSimpleSetPropertiesTest() throws Exception {
    propertyTestOut.expectedBodiesReceived("{\"name\": \"test\"}");
    producer.sendBody("direct:property-test", "{}");
    propertyTestOut.assertIsSatisfied();
  }

  @Test
  public void processorComplexSetPropertiesTest() throws Exception {
    propertyTestOut2.expectedBodiesReceived("{\"name\": \"test2\"}");
    producer.sendBody("direct:property-test2", "{}");
    propertyTestOut2.assertIsSatisfied();
  }

}
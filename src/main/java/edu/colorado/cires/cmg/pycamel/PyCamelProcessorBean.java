package edu.colorado.cires.cmg.pycamel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class PyCamelProcessorBean extends PyCamelBaseBean<PyCamelProcessor> implements Processor {


  public PyCamelProcessorBean() {
    super(PyCamelProcessor.class);
  }

  @Override
  public void process(Exchange exchange) {
    pyCamelObject.process(exchange);
  }

}

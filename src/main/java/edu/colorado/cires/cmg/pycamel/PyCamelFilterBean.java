package edu.colorado.cires.cmg.pycamel;

import org.apache.camel.Exchange;

public class PyCamelFilterBean extends PyCamelBaseBean<PyCamelFilter> {

  public PyCamelFilterBean() {
    super(PyCamelFilter.class);
  }

  public boolean filter(Exchange exchange) {
    return pyCamelObject.filter(exchange);
  }

}

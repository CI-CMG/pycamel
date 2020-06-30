package edu.colorado.cires.cmg.pycamel;

import org.apache.camel.Exchange;

public class PyCamelFunctionBean extends PyCamelBaseBean<PyCamelFunction> {

  public PyCamelFunctionBean() {
    super(PyCamelFunction.class);
  }

  public Object accept(Exchange exchange) {
    return pyCamelObject.accept(exchange);
  }

}

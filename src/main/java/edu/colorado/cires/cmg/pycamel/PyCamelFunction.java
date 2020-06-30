package edu.colorado.cires.cmg.pycamel;

import org.apache.camel.Exchange;

public interface PyCamelFunction extends PyCamelBaseObject {

  Object accept(Exchange exchange);

}

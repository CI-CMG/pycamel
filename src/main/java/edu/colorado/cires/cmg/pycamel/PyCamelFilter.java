package edu.colorado.cires.cmg.pycamel;

import org.apache.camel.Exchange;

public interface PyCamelFilter extends PyCamelBaseObject {

  boolean filter(Exchange exchange);

}

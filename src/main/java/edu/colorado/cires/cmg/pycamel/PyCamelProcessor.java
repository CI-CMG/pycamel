package edu.colorado.cires.cmg.pycamel;

import org.apache.camel.Exchange;

public interface PyCamelProcessor extends PyCamelBaseObject {

  void process(Exchange exchange);

}

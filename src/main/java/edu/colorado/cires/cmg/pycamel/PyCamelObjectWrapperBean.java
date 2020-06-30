package edu.colorado.cires.cmg.pycamel;

import org.python.core.PyObject;

public class PyCamelObjectWrapperBean extends PyCamelBaseBean<PyCamelObjectWrapper> {

  public PyCamelObjectWrapperBean() {
    super(PyCamelObjectWrapper.class);
  }

  public PyObject get() {
    return pyCamelObject.get();
  }

}

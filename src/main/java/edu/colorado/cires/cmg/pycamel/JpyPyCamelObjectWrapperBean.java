package edu.colorado.cires.cmg.pycamel;

import org.jpy.PyObject;

public class JpyPyCamelObjectWrapperBean extends PyCamelBaseBean<JpyPyCamelObjectWrapper> {

  public JpyPyCamelObjectWrapperBean() {
    super(JpyPyCamelObjectWrapper.class);
  }

  public PyObject get() {
    return pyCamelObject.get();
  }

}

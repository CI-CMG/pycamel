package edu.colorado.cires.cmg.pycamel;

import org.python.core.PyObject;

public class JythonPyCamelObjectWrapperBean extends PyCamelBaseBean<JythonPyCamelObjectWrapper> {

  public JythonPyCamelObjectWrapperBean() {
    super(JythonPyCamelObjectWrapper.class);
  }

  public PyObject get() {
    return pyCamelObject.get();
  }

}

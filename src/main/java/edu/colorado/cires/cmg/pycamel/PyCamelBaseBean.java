package edu.colorado.cires.cmg.pycamel;

import java.util.Map;

abstract class PyCamelBaseBean<T extends PyCamelBaseObject> {

  protected T pyCamelObject;
  protected final Class<T> type;
  protected String pythonModule;
  protected String pythonClass;
  protected PythonObjectFactory pythonObjectFactory;
  protected Map<String, Object> properties;

  protected PyCamelBaseBean(Class<T> type) {
    this.type = type;
  }

  public void setPythonObjectFactory(PythonObjectFactory pythonObjectFactory) {
    this.pythonObjectFactory = pythonObjectFactory;
  }

  public void setPythonModule(String pythonModule) {
    this.pythonModule = pythonModule;
  }

  public void setPythonClass(String pythonClass) {
    this.pythonClass = pythonClass;
  }

  public void setProperties(Map<String, Object> properties) {
    this.properties = properties;
  }

  public void afterPropertiesSet() {
    if (pythonObjectFactory == null) {
      throw new NullPointerException("pythonObjectFactory must be set");
    }
    pyCamelObject = pythonObjectFactory.createObject(type, pythonModule, pythonClass);
    if (properties != null) {
      pyCamelObject.setProperties(properties);
    }
    pyCamelObject.afterPropertiesSet();
  }

  public void beforeDestroy() {
    pyCamelObject.beforeDestroy();
  }
}

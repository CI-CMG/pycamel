package edu.colorado.cires.cmg.pycamel;

import java.util.Map;

interface PyCamelBaseObject {

  void setProperties(Map<String, Object> properties);

  void afterPropertiesSet();

  void beforeDestroy();
}

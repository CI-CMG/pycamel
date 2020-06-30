package edu.colorado.cires.cmg.pycamel;

public interface PythonObjectFactory {

  <T> T createObject(Class<T> interfaceType, String moduleName, String className, Object... args);

}

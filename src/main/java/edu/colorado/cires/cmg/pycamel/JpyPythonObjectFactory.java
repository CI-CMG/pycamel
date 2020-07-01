package edu.colorado.cires.cmg.pycamel;

import java.util.HashMap;
import java.util.Map;
import org.jpy.PyLib;
import org.jpy.PyModule;
import org.jpy.PyObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JpyPythonObjectFactory implements PythonObjectFactory {

  private static final Logger LOGGER = LoggerFactory.getLogger(JpyPythonObjectFactory.class);

  private String importPath;
//  private String jpyLib;
//  private String jdlLib;
//  private String pythonLib;
//  private String jpyConfig;
//
  private final Map<String, PyModule> moduleMap = new HashMap<>();

  public void setImportPath(String importPath) {
    this.importPath = importPath;
  }
//
//  public void setJpyLib(String jpyLib) {
//    this.jpyLib = jpyLib;
//  }
//
//  public void setJdlLib(String jdlLib) {
//    this.jdlLib = jdlLib;
//  }
//
//  public void setPythonLib(String pythonLib) {
//    this.pythonLib = pythonLib;
//  }
//
//  public void setJpyConfig(String jpyConfig) {
//    this.jpyConfig = jpyConfig;
//  }

  public void afterPropertiesSet() {
//    jpy.jpyLib = /Users/cslater/projects/multibeam-ingest/venv/lib/python3.8/site-packages/jpy.cpython-38-darwin.so
//    jpy.jdlLib = /Users/cslater/projects/multibeam-ingest/venv/lib/python3.8/site-packages/jdl.cpython-38-darwin.so
//    jpy.pythonLib = /Users/cslater/.pyenv/versions/3.8.3/Python.framework/Versions/3.8/lib/python3.8/config-3.8-darwin/libpython3.8.dylib
//    jpy.pythonPrefix = /Users/cslater/projects/multibeam-ingest/venv
//    jpy.pythonExecutable = /Users/cslater/projects/multibeam-ingest/venv/bin/python
//    PyLib.
//
//    if(jpyConfig != null) {
//      System.setProperty("jpy.config", jpyLib);
//    }
//
//    System.setProperty("jpy.jpyLib", jpyLib);
//    System.setProperty("jpy.jdlLib", jdlLib);
//    System.setProperty("jpy.pythonLib", pythonLib);

    if(importPath != null) {
      PyLib.startPython(importPath.split(","));
    } else {
      PyLib.startPython();
    }

    PyLib.assertPythonRuns();
    LOGGER.info(PyLib.getPythonVersion());
  }

  @Override
  public synchronized <T> T createObject(Class<T> interfaceType, String moduleName, String className, Object... args) {
    PyModule module = moduleMap.get(moduleName);
    if (module == null) {
      module = PyModule.importModule(moduleName);
      moduleMap.put(moduleName, module);
    }

    PyObject klass = module.call(className, args);

    return klass.createProxy(interfaceType);
  }


  public void beforeDestroy() {
    PyLib.stopPython();
  }

}

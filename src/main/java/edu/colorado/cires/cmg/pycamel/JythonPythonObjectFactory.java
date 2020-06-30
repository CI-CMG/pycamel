package edu.colorado.cires.cmg.pycamel;

import java.util.HashMap;
import java.util.Map;
import org.python.core.Py;
import org.python.core.PyObject;
import org.python.core.PySystemState;

public class JythonPythonObjectFactory implements PythonObjectFactory {

  private final PySystemState state = new PySystemState();
  private final PyObject importer;
  private final Map<String, PyObject> moduleMap = new HashMap<>();

  public JythonPythonObjectFactory() {
    importer = state.getBuiltins().__getitem__(Py.newString("__import__"));
  }

  private synchronized <T> T createObject(Class<T> interfaceType, String moduleName, String className, Object args[], String keywords[]) {

    PyObject module = moduleMap.get(moduleName);
    if (module == null) {
      module = importer.__call__(Py.newString(moduleName), null, null, Py.java2py(new String[]{className}));
      moduleMap.put(moduleName, module);
    }

    PyObject klass = module.__getattr__(className);

    PyObject convertedArgs[] = new PyObject[args.length];
    for (int i = 0; i < args.length; i++) {
      convertedArgs[i] = Py.java2py(args[i]);
    }
    return (T) klass.__call__(convertedArgs, keywords).__tojava__(interfaceType);
  }

  @Override
  public synchronized <T> T createObject(Class<T> interfaceType, String moduleName, String className, Object... args) {
    return createObject(interfaceType, moduleName, className, args, Py.NoKeywords);
  }

  public void beforeDestroy() throws Exception {
    state.close();
  }
}

# PyCamel


## Installing jpy
If jpy is used as a the Java-Python bridge, it must be built from source and the extension installed on the
system python or a virtual environment.  This is not required if using Jython.  PyCamel has been tested using v0.9.0 of jpy and Python 2.7.

The following is an example of how to build and install jpy using a pyenv with pyenv-virtualenv. pyenv is not required though.  
More complete documentation is located at:
- https://github.com/bcdev/jpy
- https://github.com/pyenv/pyenv
- https://www.freecodecamp.org/news/manage-multiple-python-versions-and-virtual-environments-venv-pyenv-pyvenv-a29fb00c296f

In the below examples, the APP_HOME environment variable refers to a directory containing your application that uses PyCamel.

Install specific Python version and set up a local virtual environment:
```bash
env PYTHON_CONFIGURE_OPTS="--enable-shared" pyenv install 2.7.18
cd "$APP_HOME"
pyenv virtualenv 2.7.18 my-app-2.7.18
pyenv local my-app-2.7.18
```

Install Apache Maven (https://maven.apache.org).  Set JAVA_HOME and MAVEN_HOME environment variables and update your PATH
variable.

Get and build jpy:
```bash
cd "$APP_HOME"
git clone git@github.com:bcdev/jpy.git
cd jpy
git checkout 0.9.0
python get-pip.py
python setup.py install
```

Copy the jpy jar file to your application's classpath (build/jpy-0.9.0.jar).

Take note of the file at build/jpyconfig.propeties.  These property values will
be used when configuring PyCamel.




 
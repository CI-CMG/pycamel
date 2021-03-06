from edu.colorado.cires.cmg.pycamel import JythonPyCamelObjectWrapper

class NameService(JythonPyCamelObjectWrapper):
  def __init__(self):
    self.name = None

  def get(self):
    return self

  def setProperties(self, properties):
    self.name = properties['name']

  def afterPropertiesSet(self):
    pass

  def beforeDestroy(self):
    pass

  def getName(self):
    return self.name

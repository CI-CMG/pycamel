import jpy

LoggerFactory = jpy.get_type('org.slf4j.LoggerFactory')
LOGGER = LoggerFactory.getLogger('jpy.NameService')

class NameService(object):
  def __init__(self):
    self.name = None

  def get(self):
    return self

  def setProperties(self, properties):
    self.name = properties.get('name')

  def afterPropertiesSet(self):
    pass

  def beforeDestroy(self):
    pass

  def getName(self):
    LOGGER.info("NameService.getName")
    return self.name

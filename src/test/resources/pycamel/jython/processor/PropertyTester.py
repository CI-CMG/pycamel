import json
from edu.colorado.cires.cmg.pycamel import PyCamelProcessor
from org.slf4j import LoggerFactory

LOGGER = LoggerFactory.getLogger('jython.PropertyTester')

class PropertyTester(PyCamelProcessor):
  def __init__(self):
    self.name = None

  def setProperties(self, properties):
    self.name = properties['name']

  def afterPropertiesSet(self):
    pass

  def process(self, exchange):
    LOGGER.info('PropertyTester.process')
    msg = json.loads(exchange.getIn().getBody())
    msg['name'] = self.name
    exchange.getIn().setBody(json.dumps(msg))

  def beforeDestroy(self):
    pass

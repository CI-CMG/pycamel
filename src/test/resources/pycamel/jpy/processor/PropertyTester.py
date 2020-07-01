import json
import jpy

LoggerFactory = jpy.get_type('org.slf4j.LoggerFactory')
LOGGER = LoggerFactory.getLogger('jpy.PropertyTester')

class PropertyTester(object):
  def __init__(self):
    self.name = None

  def setProperties(self, properties):
    LOGGER.info("PropertyTester.setProperties")
    self.name = properties.get('name')

  def afterPropertiesSet(self):
    pass

  def process(self, exchange):
    LOGGER.info("PropertyTester.process")
    msg = json.loads(exchange.getIn().getBody())
    msg['name'] = self.name
    exchange.getIn().setBody(json.dumps(msg))

  def beforeDestroy(self):
    pass

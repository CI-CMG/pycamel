import json
import jpy

LoggerFactory = jpy.get_type('org.slf4j.LoggerFactory')
LOGGER = LoggerFactory.getLogger('jpy.PropertyTester2')

class PropertyTester2(object):
  def __init__(self):
    self.service = None

  def setProperties(self, properties):
    # LOGGER.info("PropertyTester2.setProperties {}", properties.get('service').get())
    self.service = properties.get('service')

  def afterPropertiesSet(self):
    pass

  def process(self, exchange):
    LOGGER.info("PropertyTester2.process")
    msg = json.loads(exchange.getIn().getBody())
    msg['name'] = self.service.get().getName()
    exchange.getIn().setBody(json.dumps(msg))

  def beforeDestroy(self):
    pass

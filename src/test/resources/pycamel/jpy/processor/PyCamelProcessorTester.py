import json
import jpy

LoggerFactory = jpy.get_type('org.slf4j.LoggerFactory')
LOGGER = LoggerFactory.getLogger('jpy.PyCamelProcessorTester')

class PyCamelProcessorTester(object):

  def setProperties(self, properties):
    pass

  def afterPropertiesSet(self):
    pass

  def process(self, exchange):
    LOGGER.info("PyCamelProcessorTester.process")
    msg = json.loads(exchange.getIn().getBody())
    msg['foo'] = 'bar'
    exchange.getIn().setBody(json.dumps(msg))

  def beforeDestroy(self):
    pass

import json
from edu.colorado.cires.cmg.pycamel import PyCamelProcessor

class PyCamelProcessorTester(PyCamelProcessor):

  def setProperties(self, properties):
    pass

  def afterPropertiesSet(self):
    pass

  def process(self, exchange):
    msg = json.loads(exchange.getIn().getBody())
    msg['foo'] = 'bar'
    exchange.getIn().setBody(json.dumps(msg))

  def beforeDestroy(self):
    pass

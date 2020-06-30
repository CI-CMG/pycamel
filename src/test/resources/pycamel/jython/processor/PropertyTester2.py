import json
from edu.colorado.cires.cmg.pycamel import PyCamelProcessor

class PropertyTester2(PyCamelProcessor):
  def __init__(self):
    self.service = None

  def setProperties(self, properties):
    self.service = properties['service']

  def afterPropertiesSet(self):
    pass

  def process(self, exchange):
    msg = json.loads(exchange.getIn().getBody())
    msg['name'] = self.service.get().getName()
    exchange.getIn().setBody(json.dumps(msg))

  def beforeDestroy(self):
    pass

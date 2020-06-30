import json
from edu.colorado.cires.cmg.pycamel import PyCamelProcessor

class PropertyTester(PyCamelProcessor):
  def __init__(self):
    self.name = None

  def setProperties(self, properties):
    self.name = properties['name']

  def afterPropertiesSet(self):
    pass

  def process(self, exchange):
    msg = json.loads(exchange.getIn().getBody())
    msg['name'] = self.name
    exchange.getIn().setBody(json.dumps(msg))

  def beforeDestroy(self):
    pass

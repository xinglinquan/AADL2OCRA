package aadl2ocra.template

import org.osate.aadl2.Connection
import java.util.List
class ConnectionTemplate {
	def static genConnection(List<Connection> connections)'''
	«FOR connection : connections»
	«IF(connection.source.context !== null&&connection.destination.context === null)»
		CONNECTION «connection.destination.connectionEnd.name» := «connection.source.context.name».«connection.source.connectionEnd.name»;
	«ENDIF»
	«IF(connection.source.context === null&&connection.destination.context !== null)»
		CONNECTION «connection.destination.context.name».«connection.destination.connectionEnd.name» := «connection.source.connectionEnd.name»;
	«ENDIF»
	«IF(connection.source.context !== null&&connection.destination.context !== null)»
		CONNECTION «connection.destination.context.name».«connection.destination.connectionEnd.name» := «connection.source.context.name».«connection.source.connectionEnd.name»;
	«ENDIF»
	«ENDFOR»
	'''
}
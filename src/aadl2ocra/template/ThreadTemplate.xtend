package aadl2ocra.template

import org.osate.aadl2.ThreadImplementation
import org.osate.aadl2.PropertyAssociation
import org.osate.aadl2.impl.StringLiteralImpl

class ThreadTemplate {
	def static genThread(ThreadImplementation thread)'''
		COMPONENT «thread.name»
	    «IF thread.allFeatures.size > 0»
		INTERFACE
		«FeatureTemplate.genFeature(thread.allFeatures)»
	    «ENDIF»		
		«thread.dealContract»		
	    «IF thread.allSubcomponents.size >0»
		REFINEMENT
	    «FOR component:thread.allSubcomponents»
		SUB «component.name» : «component.getComponentImplementation.name»;
	    «ENDFOR»
	    «ENDIF»
	    «IF thread.allConnections.size >0»
		«ConnectionTemplate.genConnection(thread.allConnections)»    
	    «ENDIF»		
		«thread.dealRefinedBy»
		
	'''
    def static dealContract(ThreadImplementation thread)'''
    	«IF thread.allPropertyAssociations!==null»
    	    «FOR PropertyAssociation pa : thread.allPropertyAssociations»
    	    	«IF pa.property.namespace.name.equals("OCRA")&&pa.property.name.equals("Contract")»
    	    		«FOR value : pa.ownedValues»
    	    			«var ownedValue=value.ownedValue»
    	    			«switch ownedValue{
    	    				StringLiteralImpl:
    							ownedValue.value
    	    				default:
    	    					ownedValue.toString
    	    			}»
    	    		«ENDFOR»
    	    	«ENDIF»
    	    «ENDFOR»	
    	 «ENDIF»	
    '''
    def static dealRefinedBy(ThreadImplementation thread)'''
    	«IF thread.allPropertyAssociations!==null»
    	    «FOR PropertyAssociation pa : thread.allPropertyAssociations»
    	    	«IF pa.property.namespace.name.equals("OCRA")&&pa.property.name.equals("RefinedBy")»
    	    	    «FOR value : pa.ownedValues»
    	    	    	«var ownedValue=value.ownedValue»
    	    	    	«switch ownedValue{
    	    	    	    StringLiteralImpl:
    							ownedValue.value
    	    	    	    default:
    	    	    	    	ownedValue.toString
    	    	    	}»
    	    	    «ENDFOR»
    	    	 «ENDIF»
    	    «ENDFOR»	
    	 «ENDIF»	
    	 
    '''	
}
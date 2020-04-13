package aadl2ocra.template


import org.osate.aadl2.ProcessImplementation
import org.osate.aadl2.ThreadImplementation
import org.osate.aadl2.PropertyAssociation
import org.osate.aadl2.impl.StringLiteralImpl
import aadl2ocra.utils.StringUtils

class ProcessTemplate {
	//def static genProcess(ProcessSubcomponent processSubcomponent){
		//System.out.println(processSubcomponent.classifier);
		//System.out.println()
	//}
	def static genProcess(ProcessImplementation process)'''
		COMPONENT «StringUtils.convertPoint(process.name)»
	    «IF process.allFeatures.size > 0»
		INTERFACE
		«FeatureTemplate.genFeature(process.allFeatures)»
	    «ENDIF»		
		«process.dealContract»		
	    «IF process.allSubcomponents.size >0»
		REFINEMENT
	    «FOR component:process.allSubcomponents»
		SUB «component.name» : «StringUtils.convertPoint(component.getComponentImplementation.name)»;
	    «ENDFOR»
	    «ENDIF»
	    «IF process.allConnections.size >0»
		«ConnectionTemplate.genConnection(process.allConnections)»    
	    «ENDIF»		
		«process.dealRefinedBy»
		
	    «IF process.ownedThreadSubcomponents.size >0»
	    «FOR threadsubcomponent : process.ownedThreadSubcomponents»
		«var ThreadImplementation threadImplementation = threadsubcomponent.subcomponentType as ThreadImplementation»
		«ThreadTemplate.genThread(threadImplementation)»
	    «ENDFOR»
	    «ENDIF»
	'''
    def static dealContract(ProcessImplementation process)'''
    	«IF process.allPropertyAssociations!==null»
    	    «FOR PropertyAssociation pa : process.allPropertyAssociations»
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
    def static dealRefinedBy(ProcessImplementation process)'''
    	«IF process.allPropertyAssociations!==null»
    	    «FOR PropertyAssociation pa : process.allPropertyAssociations»
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
package aadl2ocra.workflow

import org.osate.aadl2.SystemImplementation
import aadl2ocra.template.ConnectionTemplate
import aadl2ocra.template.FeatureTemplate
import aadl2ocra.template.ProcessTemplate
import org.osate.aadl2.PropertyAssociation
import org.osate.aadl2.impl.StringLiteralImpl
import org.osate.aadl2.ProcessImplementation
import aadl2ocra.utils.FileUtils
import aadl2ocra.utils.StringUtils

class GenerateOss {
 
 
    def static void generate(SystemImplementation system,String name) {
           FileUtils.createFile("oss",name,systemcompile(system).toString);

	}
	
    def static systemcompile(SystemImplementation system)'''
    	COMPONENT «StringUtils.convertPoint(system.name)» system
    	«IF system.allFeatures.size > 0»    	
		INTERFACE
		«FeatureTemplate.genFeature(system.allFeatures)»
    	«ENDIF»    	
    	«system.dealContract»    	
    	«IF system.allSubcomponents.size >0»	
    	REFINEMENT
    	«FOR Subcomponent:system.allSubcomponents»    	
    	SUB «Subcomponent.name» : «StringUtils.convertPoint(Subcomponent.getSubcomponentType.name)»;
    	«ENDFOR»
    	«ENDIF»
    	«IF system.allConnections.size >0»
    	«ConnectionTemplate.genConnection(system.allConnections)»    
    	«ENDIF»    	
    	«system.dealRefinedBy»    	
    	«IF system.ownedSystemSubcomponents.size >0»
    	«FOR subcomponent:system.ownedSystemSubcomponents»
		«var SystemImplementation systemImplementation = subcomponent.subcomponentType as SystemImplementation»
		«systemImplementation.compile»
    	«ENDFOR»
    	«ENDIF»
    	«IF system.ownedProcessSubcomponents.size >0»
    	«FOR processsubcomponent : system.ownedProcessSubcomponents»
		«var ProcessImplementation processImplementation = processsubcomponent.subcomponentType as ProcessImplementation»
		«ProcessTemplate.genProcess(processImplementation)»
    	«ENDFOR»
    	«ENDIF»
    	
    '''
    def static compile(SystemImplementation system)'''
		COMPONENT «StringUtils.convertPoint(system.name)»
    	«IF system.allFeatures.size > 0»
		INTERFACE
		«FeatureTemplate.genFeature(system.allFeatures)»
    	«ENDIF»		
		«system.dealContract»		
    	«IF system.allSubcomponents.size >0»
		REFINEMENT
    	«FOR component:system.allSubcomponents»
		SUB «component.name» : «StringUtils.convertPoint(component.getSubcomponentType.name)»;
    	«ENDFOR»
    	«ENDIF»
    	«IF system.allConnections.size >0»
		«ConnectionTemplate.genConnection(system.allConnections)»    
    	«ENDIF»		
		«system.dealRefinedBy»		
    	«IF system.ownedSystemSubcomponents.size >0»
    	«FOR subcomponent:system.ownedSystemSubcomponents»
		«var SystemImplementation systemImplementation = subcomponent.subcomponentType as SystemImplementation»
		«systemImplementation.compile»
    	«ENDFOR»
    	«ENDIF»
    	«IF system.ownedProcessSubcomponents.size >0»
    	«FOR processsubcomponent : system.ownedProcessSubcomponents»
		«var ProcessImplementation processImplementation = processsubcomponent.subcomponentType as ProcessImplementation»
		«ProcessTemplate.genProcess(processImplementation)»
    	«ENDFOR»
    	«ENDIF»
    	
    '''
    def static dealContract(SystemImplementation system)'''
    	«IF system.allPropertyAssociations!==null»
    	    «FOR PropertyAssociation pa : system.allPropertyAssociations»
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
    def static dealRefinedBy(SystemImplementation system)'''
    	«IF system.allPropertyAssociations!==null»
    	    «FOR PropertyAssociation pa : system.allPropertyAssociations»
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
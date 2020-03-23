package aadl2ocra.workflow

import org.osate.aadl2.SystemImplementation
import aadl2ocra.tool.Tools
import aadl2ocra.template.*
import org.osate.aadl2.PropertyAssociation
import org.osate.aadl2.impl.StringLiteralImpl

class GenerateOss {
 
 
    def static void generate(SystemImplementation system) {
           Tools.createFile("oss","system.oss",systemcompile(system).toString);
           //System.out.println(systemcompile(system).toString);
          // System.out.println(system.name);
           //System.out.println("1 "+system.allPropertyAssociations);
          /// System.out.println("2 "+system.allPropertyAssociations.get(0).property.namespace.name);
          // System.out.println("3 "+system.allPropertyAssociations.get(0).property.name);
          // System.out.println("4 "+system.allPropertyAssociations.get(0).ownedValues.get(0).ownedValue);
	}
	
    def static systemcompile(SystemImplementation system)'''
    	COMPONENT «system.name» system
    	«IF system.allFeatures.size > 0»    	
		INTERFACE
		«FeatureTemplate.genFeature(system.allFeatures)»
    	«ENDIF»    	
    	«system.dealContract»    	
    	«IF system.allSubcomponents.size >0»	
    	REFINEMENT
    	«FOR Subcomponent:system.allSubcomponents»
    	SUB «Subcomponent.name» : «Subcomponent.getComponentImplementation.name»;
    	«ENDFOR»
    	«ENDIF»
    	«IF system.allConnections.size >0»
    	«ConnectionTemplate.genConnection(system.allConnections)»    
    	«ENDIF»    	
    	«system.dealRefinedBy»
    	
    	«IF system.allSubcomponents.size >0»
    	«FOR subcomponent:system.allSubcomponents»
    	«var SystemImplementation systemImplementation = subcomponent.subcomponentType as SystemImplementation»
    	«systemImplementation.compile»
    	«ENDFOR»
    	«ENDIF»
    '''
    def static compile(SystemImplementation system)'''
		COMPONENT «system.name»
    	«IF system.allFeatures.size > 0»
		INTERFACE
		«FeatureTemplate.genFeature(system.allFeatures)»
    	«ENDIF»		
		«system.dealContract»		
    	«IF system.allSubcomponents.size >0»
		REFINEMENT
    	«FOR component:system.allSubcomponents»
		SUB «component.name» : «component.getSubcomponentType»;
    	«ENDFOR»
    	«ENDIF»
    	«IF system.allConnections.size >0»
		«ConnectionTemplate.genConnection(system.allConnections)»    
    	«ENDIF»		
		«system.dealRefinedBy»
		
    	«IF system.allSubcomponents.size >0»
    	«FOR subcomponent:system.allSubcomponents»
		«var SystemImplementation systemImplementation = subcomponent.subcomponentType as SystemImplementation»
		«systemImplementation.compile»
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
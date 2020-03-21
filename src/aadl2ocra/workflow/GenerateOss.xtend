package aadl2ocra.workflow

import org.osate.aadl2.SystemImplementation
import aadl2ocra.tool.Tools
import org.osate.aadl2.Feature
import org.osate.aadl2.DataPort
import org.osate.aadl2.EventPort
import org.osate.aadl2.EventDataPort
import org.osate.aadl2.DataAccess
import aadl2ocra.template.*
class GenerateOss {
 
 
    def static void generate(SystemImplementation system) {
           Tools.createFile("oss","system.oss",systemcompile(system).toString);
           //System.out.println(systemcompile(system).toString);
           System.out.println(system.name);
	}
	
    def static systemcompile(SystemImplementation system)'''
    	COMPONENT «system.name» system
    	«IF system.allFeatures.size > 0»
		INTERFACE
		«FeatureTemplate.genFeature(system.allFeatures)»
    	«ENDIF»
    	CONTRACT
    	
    	«IF system.allSubcomponents.size >0»
    	REFINEMENT
    	«FOR Subcomponent:system.allSubcomponents»
    	SUB «Subcomponent.name» : «Subcomponent.getComponentImplementation.name»;
    	«ENDFOR»
    	«ENDIF»
    	«IF system.allConnections.size >0»
    	«ConnectionTemplate.genConnection(system.allConnections)»    
    	«ENDIF»
    	CONTRACT REFINEDBY
    	
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
    	CONTRACT
    	
    	«IF system.allSubcomponents.size >0»
    	REFINEMENT
    	«FOR component:system.allSubcomponents»
    	SUB «component.name» : «component.getSubcomponentType»;
    	«ENDFOR»
    	«ENDIF»
    	«IF system.allConnections.size >0»
    	«ConnectionTemplate.genConnection(system.allConnections)»    
    	«ENDIF»
    	CONTRACT REFINEDBY
    	
    	«IF system.allSubcomponents.size >0»
    	«FOR component:system.allSubcomponents»
    	«»
    	«ENDFOR»
    	«ENDIF»
    '''
}
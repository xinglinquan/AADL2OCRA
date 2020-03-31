package aadl2ocra.workflow

import org.osate.aadl2.SystemImplementation
import aadl2ocra.tool.Tools
import aadl2ocra.template.*
import org.osate.aadl2.PropertyAssociation
import org.osate.aadl2.impl.StringLiteralImpl
import org.osate.aadl2.ProcessImplementation
class GenerateOss {
 
 
    def static void generate(SystemImplementation system) {
           Tools.createFile("oss","system.oss",systemcompile(system).toString);
           /*for (Subcomponent:system.allSubcomponents){
           	System.out.println("name:"+Subcomponent.name);
           	System.out.println("Typename:"+Subcomponent.getClassifier);
           	System.out.println("Typename1:"+Subcomponent.getSubcomponentType);
           	System.out.println("Typename2:"+Subcomponent.getComponentImplementation);
           	System.out.println("Typename3:"+Subcomponent.getOwnedPrototypeBindings);
           	System.out.println("Typename4:"+Subcomponent.getImplementationReferences);
           	System.out.println("Typename5:"+Subcomponent.getComponentType);
           	System.out.println("Typename6:"+Subcomponent.getAllClassifier);
           	}*/
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
    	
    	SUB «Subcomponent.name» : «Subcomponent.getSubcomponentType.name»;
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
		COMPONENT «system.name»
    	«IF system.allFeatures.size > 0»
		INTERFACE
		«FeatureTemplate.genFeature(system.allFeatures)»
    	«ENDIF»		
		«system.dealContract»		
    	«IF system.allSubcomponents.size >0»
		REFINEMENT
    	«FOR component:system.allSubcomponents»
		SUB «component.name» : «component.getSubcomponentType.name»;
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
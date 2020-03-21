package aadl2ocra.template

import org.osate.aadl2.Feature
import java.util.List
import org.osate.aadl2.DataPort
import org.osate.aadl2.EventPort
import org.osate.aadl2.EventDataPort
import org.osate.aadl2.DataAccess

class FeatureTemplate {
	def static genFeature(List<Feature> features)'''
	«FOR feature : features»
		«switch feature{
				DataPort:'''
					«IF feature.in == true && feature.out == false»
						INPUT PORT «feature.name» : «feature.dealClassisfy»
					«ENDIF»
					«IF feature.in == false && feature.out == true»
						OUTPUT PORT «feature.name» : «feature.dealClassisfy»
					«ENDIF»
					«IF feature.in == true && feature.out == true»
						INPUT PORT «feature.name»_1 : «feature.dealClassisfy»
						OUTPUT PORT «feature.name»_2 : «feature.dealClassisfy»
					«ENDIF»
					'''
					EventPort:'''
					«IF feature.in == true && feature.out == false»
						INPUT PORT «feature.name» : event;
					«ENDIF»
					«IF feature.in == false && feature.out == true»
						OUTPUT PORT «feature.name» :  event;
					«ENDIF»
					«IF feature.in == true && feature.out == true»
						INPUT PORT «feature.name»_1 :  event;
						OUTPUT PORT «feature.name»_2 :  event;
					«ENDIF»
					'''
					EventDataPort:'''
					«IF feature.in == true && feature.out == false»
						INPUT PORT «feature.name»_1 : «feature.dealClassisfy»
						INPUT PORT «feature.name»_2 : event;
					«ENDIF»
					«IF feature.in == false && feature.out == true»
						OUTPUT PORT «feature.name»_1 : «feature.dealClassisfy»
						OUTPUT PORT «feature.name»_2 : event;
					«ENDIF»
					«IF feature.in == true && feature.out == true»
						INPUT PORT «feature.name»_1 : «feature.dealClassisfy»
						INPUT PORT «feature.name»_2 : event;
						OUTPUT PORT «feature.name»_3 : «feature.dealClassisfy»
						OUTPUT PORT «feature.name»_4 : event;
					«ENDIF»
					'''
		}»
	«ENDFOR»
	'''
	def static dealClassisfy(Feature feature)'''
		«IF feature.classifier !== null»
			boolean;
		«ELSE»
			real;
		«ENDIF»
	'''
}
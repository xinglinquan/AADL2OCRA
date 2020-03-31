package aadl2ocra.template

import org.osate.aadl2.PropertyAssociation
import org.osate.aadl2.impl.NamedValueImpl
import org.osate.aadl2.impl.StringLiteralImpl
import org.osate.aadl2.impl.ListValueImpl
import org.osate.aadl2.EnumerationLiteral
import org.osate.aadl2.AbstractNamedValue
import org.osate.aadl2.Classifier
import org.osate.aadl2.impl.DataTypeImpl
import org.osate.aadl2.PropertyExpression

class PropertyTemplate {
	def static dealProperty(PropertyAssociation property) '''
		«FOR value : property.ownedValues»
			«var ownedValue=value.ownedValue»
			«switch ownedValue{
				NamedValueImpl:
					ownedValue.namedValue.parseNamedValue
				ListValueImpl:'''
					{«ownedValue.parseListValue»};
				'''					
				default:
					ownedValue.toString
			}
			»
		«ENDFOR»		
	'''
	def static parseNamedValue(AbstractNamedValue  value) {
		switch value{
			EnumerationLiteral:
				if(value.name.toString.equals("Boolean"))
				return "boolean;"
			default:
				if(value.class.name.toString.equals("Boolean"))
				return "boolean;"
		}
	}
	def static parseListValue(ListValueImpl value){
		var String temp ="";
		
		//System.out.println(value.ownedListElements.get(0));
		for(var i =0;i<value.ownedListElements.size;i++){
			var PropertyExpression expression =value.ownedListElements.get(i)
			switch expression{
				StringLiteralImpl:					
					if(i!=value.ownedListElements.size-1){
						System.out.println(i)
						temp+=expression.value+","
						}
					else
						temp+=expression.value
				default:
					temp+=expression.toString
			}
		}
		return temp
		/*for(expression:value.ownedListElements){
			switch expression{
				StringLiteralImpl:
					temp+=expression.value+","
				//NamedValueImpl:
					//return 	expression.namedValue.parseNamedValue
				//ClassifierValueImpl:
					//return expression.classifier.parseClassifier
				//IntegerLiteralImpl:
					//return expression
				default:
					return "//PropertyTemplate.xtend asdasd "+expression.toString+ " " + expression.class
		}
		
		}*/
	}	

	def static parseClassifier(Classifier classifier){
		var clazz=classifier
		try {
			var type=(clazz as DataTypeImpl).name
			return type
		} catch (Exception exception) {
			return "Classifier can't be cast to DataTypeImpl . fdafds in PropertyTemplate.xtend !! \n"
		}
	}
}
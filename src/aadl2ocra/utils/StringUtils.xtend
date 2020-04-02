package aadl2ocra.utils

class StringUtils {
	
	def static convert(String str){
		return str.toLowerCase.replace(".","_")
	}

	def static convertPoint(String str){
		return str.replace(".","_")
	}	

	def static clear(String str){
		return ""
	}
	
	def static convertNo2Low(String str){
		return str.replace(".","_")
	}
	def static removeImpl(String str){
		val length=str.length
		if(length>0)
			return str.substring(0,length-5)
		else
			return str
	}
	def static removeFalse(String str){
		val length=str.length
		if(length>0)
			return str.substring(0,length-6)
		else
			return str
	}
	def static removeLastChar(String str){
		val length=str.length
		if(length>0)
			return str.substring(0,length-1)
		else
			return str
	}
	def static remove5Char(String str){
		val length=str.length
		if(length>0)
			return str.substring(0,length-5)
		else
			return str
	}
	def static clearspace(String str){
		return str.replaceAll("\r|\n","")
	}
	def static clearblank(String str){
		return str.replaceAll(" ","")
	}
	def static clearTab(String str){
		return str.replaceAll("\\t","")
	}
	def static dealMultipleSpace(String str){
		return str.replaceAll("\\s+"," ")
	}
	
	def static formatParam(String str){
		var String temp = str;
		temp = temp.replaceAll("(; )+","; ")
		if(temp.endsWith(", ")){
			temp = temp.substring(0,temp.length-2)
		}
		if(temp.startsWith(", ")){
			temp = temp.substring(2)
		}
		if(temp.startsWith("; ")){
			temp = temp.substring(2)
		}
		if(temp.endsWith("; ")){
			temp = temp.substring(0,temp.length-2)
		}
		return temp
	}
}
package aadl2ocra.function;

public class CheckContractRefinement extends OcraFunction{
	public static final String FUNCTION_NAME = "ocra_check_refinement";
	@Override
	protected void prepareCommands() {
		String cmd  = FUNCTION_NAME+" -i D:\\osate237\\runtime-EclipseApplication\\AADL_OCRA\\oss\\system.oss";
		//if( getContractModel() != null ){
			//cmd+=" -i "+getContractModel();
		//}
		commands.add(cmd);
		commands.add("quit");
	}
}

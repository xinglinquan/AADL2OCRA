package aadl2ocra.function;

import aadl2ocra.actions.ContractRefinementEntry;

public class CheckContractRefinement extends OcraFunction{
	public static final String FUNCTION_NAME = "ocra_check_refinement";
	@Override
	protected void prepareCommands() {
		String cmd  = FUNCTION_NAME+" -i "+ContractRefinementEntry.projectpath+"oss/"+ContractRefinementEntry.sysimpl.getType().getFullName()+".oss";
		//if( getContractModel() != null ){
			//cmd+=" -i "+getContractModel();
		//}
		commands.add("set on_failure_script_quits");
		commands.add(cmd);
		commands.add("quit");
	}
}

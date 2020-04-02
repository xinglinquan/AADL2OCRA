package aadl2ocra.tool;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import aadl2ocra.function.OcraFunction;
import aadl2ocra.utils.FileUtils;
public class OcraRunner {
	/** The tool executable. */
	protected final String ocraExecutable = "G:\\ocra\\ocra-1.4.0\\bin\\win64\\ocra-win64.exe"; // executable name of the tool
	/** The cmd file. */
	protected File cmdFile;
	/** The process that runs ocra . */
	protected Process ocraRunnerProcess;
	/** The tool function. */
	protected OcraFunction ocraFunction;
	public OcraFunction getOcraFunction() {
		return ocraFunction;
	}
	public void setOcraFunction(OcraFunction ocraFunction) {
		this.ocraFunction = ocraFunction;
	}
	public File getCmdFile() {
		return cmdFile;
	}
	public void setCmdFile(File cmdFile) {
		this.cmdFile = cmdFile;
	}
	public Process getOcraRunnerProcess() {
		return ocraRunnerProcess;
	}
	public void setOcraRunnerProcess(Process ocraRunnerProcess) {
		this.ocraRunnerProcess = ocraRunnerProcess;
	}
	public String getOcraExecutable() {
		return ocraExecutable;
	}
	/**
	 * Kill the tool runner process.
	 */
	public void killToolRunnerProcess(){
		if( ocraRunnerProcess != null )
		{
			System.out.println("Killing process");

			ocraRunnerProcess.destroy();

			System.out.println("Process killed");

			ocraRunnerProcess = null;
		}
	}
	protected File createCmdFile(){
		final String cmd = getCmdFilePayload();
		if( cmd == null ){
			return null;
		}

		System.out.println("Command string:\n" + cmd);

		cmdFile = FileUtils.CmdFile("cmd","system.cmd",cmd);

		return cmdFile;

	}
	protected String getCmdFilePayload(){
		if( ocraFunction == null ){
			System.out.println("Internal error: ocraFunction is null");
			return null;
		}

		final StringBuffer cmdBuffer = new StringBuffer();
		cmdBuffer.append(ocraFunction.getCommandsAsString());

		return cmdBuffer.toString();
	}
	protected String[] getCmdLine() throws Exception{
		final List<String> cmdArray = new ArrayList<String>();

		cmdArray.add(getOcraExecutable());

		if( ocraFunction != null && createCmdFile() != null ){
			cmdArray.add("-source");
			//System.out.println(cmdFile.getPath());
			//cmdArray.add(cmdFile.getCanonicalPath());
			cmdArray.add("D:\\osate237\\runtime-EclipseApplication\\AADL_OCRA\\cmd\\system.cmd");
		}
		else{
			System.out.println("test");
			cmdArray.add("-h");
		}
		for(String temp : cmdArray) {
			System.out.println(temp);
		}
		return cmdArray.toArray(new String[0]);
	}
	protected class ToolResult{

		/** The task for running the tool. */
		private final OcraRunnerTask toolRunningTask;

		/** Constructor. 
		 * @throws Exception */
		protected ToolResult() throws Exception{
			this.toolRunningTask = new OcraRunnerTask(getCmdLine());
		}
		public void get() {
			final ExecutorService executor = Executors.newSingleThreadExecutor();
			executor.submit(toolRunningTask);
		}
	}
	
	public void runOcra() throws Exception {
		final ToolResult toolResult = new ToolResult();
		toolResult.get();
	}
}

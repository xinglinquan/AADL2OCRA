package aadl2ocra.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class OcraRunnerTask implements Callable{
	private String[] cmdArray;
	/** The process that runs the tool */
	private Process ocraRunnerProcess = null;
	
	protected OcraRunnerTask(String[] cmdArray){
		this.cmdArray   = cmdArray;
	}
	@Override
	public Object call() throws Exception {
		ocraRunnerProcess = Runtime.getRuntime().exec(cmdArray);
		final StreamReader outputStreamReader = new StreamReader(ocraRunnerProcess.getInputStream());
		final StreamReader errorStreamReader  = new StreamReader(ocraRunnerProcess.getErrorStream());
		
		outputStreamReader.start();
		errorStreamReader.start();

		final int exitStatus = ocraRunnerProcess.waitFor();

		outputStreamReader.join();
		errorStreamReader.join();
		
		// for debug purpose

		System.out.println(outputStreamReader.getReadString());
		System.out.println(errorStreamReader.getReadString());
		return null;
		
	}
	/**
	 * Reads the input stream in a thread.
	 */
	protected class StreamReader extends Thread{
		/** The input stream. */
		private final InputStream inputStream;

		/** The result as a list of strings. */
		private final List<String> result = new ArrayList<String>();

		/** The exception thrown during the read. */
		private IOException thrownException;

		/**
		 *  Constructor.
		 *
		 * @param inputStream the input stream
		 */
		public StreamReader(InputStream inputStream){
			this.inputStream = inputStream;
		}
		@Override
		public void run(){
			try{
				final InputStreamReader isr = new InputStreamReader(inputStream);
				final BufferedReader br     = new BufferedReader(isr);
			
				String line = null;
				while ((line = br.readLine()) != null){
					result.add(line);
				}
				br.close();
				isr.close();
			}
			catch (IOException ioe){
				thrownException = ioe;
			}
		}
		
		/**
		 * Returns the read lines if the read was successful or further throws
		 * the IOException that was thrown during {@link #run()}.
		 *
		 * @return the read lines
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		public List<String> getReadLines() throws IOException{
			if (thrownException != null){
				throw thrownException;
			}
			
			return result;
		}

		/**
		 * Returns the read lines as a single string if the read was successful
		 * or further throws the IOException that was thrown during
		 * {@link #run()}.
		 *
		 * @return the read string
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		public String getReadString() throws IOException{
			if (thrownException != null){
				throw thrownException;
			}
			
			final StringBuffer res = new StringBuffer();
			for (String line : result){
				res.append(line).append("\n");
			}
			
			return res.toString();
		}
	}
}

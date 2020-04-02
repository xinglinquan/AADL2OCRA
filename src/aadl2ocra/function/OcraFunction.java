package aadl2ocra.function;

import java.util.ArrayList;
import java.util.List;

public abstract class OcraFunction {
	private String contractModel;
	public String getContractModel() {
		return contractModel;
	}
	public void setContractModel(String contractModel) {
		this.contractModel = contractModel;
	}
	protected final List<String> commands = new ArrayList<String>();
	protected abstract void prepareCommands();
	public String getCommandsAsString()
	{
		prepareCommands();

		final StringBuffer buffer = new StringBuffer();

		commands.forEach(command -> {buffer.append(command.toString()).append(System.lineSeparator());});

		// remove last new line
		final int lastNewLine = buffer.lastIndexOf(System.lineSeparator());
		if( lastNewLine >= 0 )
		{
			buffer.deleteCharAt(lastNewLine);
		}

		return buffer.toString();
	}
}

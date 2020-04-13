package aadl2ocra.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import aadl2ocra.ui.Contract;
import aadl2ocra.ui.Start;

import org.eclipse.core.resources.IFile;

public class Add_EditContractEntry implements IObjectActionDelegate{
	org.eclipse.core.resources.IFile iFile;
	@Override
	public void run(IAction arg0) {
		// TODO Auto-generated method stub
		//Contract con = new Contract(get_IFile());
		//con.setVisible(true);
		Start start = new Start(get_IFile());
		start.setVisible(true);
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
		TreeSelection treeSelection = (TreeSelection) selection;
		iFile = (IFile) treeSelection.getFirstElement();
	}

	@Override
	public void setActivePart(IAction arg0, IWorkbenchPart arg1) {
		// TODO Auto-generated method stub
		
	}
	public String get_IFile() {
		String s = this.iFile.getLocation().toFile().getAbsolutePath();
		if(s.indexOf(".aadl") == -1)
		{
			System.out.println("error!");
		}
		System.out.println("name is "+s);
		return s;
	}
}

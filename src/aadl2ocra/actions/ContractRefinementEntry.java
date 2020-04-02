package aadl2ocra.actions;



import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.internal.Workbench;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.SystemImplementation;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instance.impl.SystemInstanceImpl;
import org.osate.aadl2.modelsupport.resources.OsateResourceUtil;
import org.osate.workspace.IAadlElement;
import org.osate.workspace.IAadlProject;

import aadl2ocra.generator.Generation;
import aadl2ocra.tool.OcraRunner;
import aadl2ocra.ui.MainFrame;
import aadl2ocra.ui.MainFrame2;
import aadl2ocra.function.CheckContractRefinement;
import aadl2ocra.generator.*;

public class ContractRefinementEntry implements IObjectActionDelegate {
	org.eclipse.core.resources.IFile iFile;
	private static String projectname;
	/** */
	private SystemInstance _si = null;
	private SystemImplementation sysimpl = null;
	/** */
	private static IProject cproject;
	private static ISelectionService selectionService;
	@Override
	public void run(IAction iaction) {
		// TODO Auto-generated method stub
		MainFrame2 mf = new MainFrame2(sysimpl);
		System.out.println("1  "+_si);
		System.out.println("2  "+sysimpl.getType().getFullName());
		System.out.println("3  "+sysimpl.getAllSubcomponents().get(0).getSubcomponentType().getFullName());
		mf.setVisible(true);
		preCprjCreate();
		OcraRunner ocraRunner = new OcraRunner();
		CheckContractRefinement CCR = new CheckContractRefinement();
		System.out.println("before");
		ocraRunner.setOcraFunction(CCR);
		try {
			ocraRunner.runOcra();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("after");
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		System.out.println("bbb");
		// TODO Auto-generated method stub
		TreeSelection treeSelection = (TreeSelection) selection;
		iFile = (IFile) treeSelection.getFirstElement();
		ContractRefinementEntry.selectionService = Workbench.getInstance().getActiveWorkbenchWindow().getSelectionService();
		// IProject project = null;
		if (selection instanceof IStructuredSelection) {
			Object element = ((IStructuredSelection) selection).getFirstElement();

			if (element instanceof IResource) {
				cproject = ((IResource) element).getProject();
			} else if (element instanceof IAadlProject) {
				IAadlProject jProject = ((IAadlElement) element).getAadlProject();
				cproject = jProject.getProject();
			}
		}

		if ((!(selection.isEmpty())) && (selection instanceof IStructuredSelection)) {
			Object obj = ((IStructuredSelection) selection).getFirstElement();
			// System.out.println(obj instanceof IFile);
			if (obj instanceof IFile) {
				final IFile file = (IFile) obj;
				System.out.println(file.getFullPath().toString());
				//if (file.getFileExtension().toLowerCase().equals("aaxl2")) {
					org.osate.aadl2.instance.SystemInstance model;
					XtextResourceSet resourceSet = OsateResourceUtil.getResourceSet();
					String sp = file.getFullPath().toString();
					System.out.println(sp);
					Resource resource = resourceSet.getResource(URI.createPlatformResourceURI(sp, false), true);
					if (resource.getContents().size() > 0) {

						model = (org.osate.aadl2.instance.SystemInstance) resource.getContents().get(0);
						_si = model;
						// Template.abolutePathOfSystem=file.getFullPath().toString();
						sysimpl=getSystemImplementation2((SystemInstanceImpl) _si);
					} else {
						model = null;
						
					}
					System.out.println(model);
					 System.out.println("aaa");
				//}

			}
		}
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
	
	SystemImplementation getSystemImplementation2(SystemInstanceImpl _si) {

		for (ComponentImplementation sysi : _si.getInstantiatedObjects()) {
			if (sysi instanceof SystemImplementation) {
				return (SystemImplementation) sysi;
			}
		}
		return null;
	}

	/**
	 * systemInstanceImpl��systemImplementation�Ĺ�ϵ Each SystemImplementation is a
	 * ComponentImplementation of SystemInstanceImpl GetInstantiatedObjects()
	 * returns a list of ComponentImplementation
	 */
	public void preCprjCreate() {
		//Tools.setCurrentProject(GeneratorAction.cproject);
		int state = Generation.codeGeneration(sysimpl, cproject, "D:\\osate2-2.3.7-vfinal-win32.win32.x86_64\\runtime-EclipseApplication\\AADL_OCRA\\");
		if(state==1) {
			System.out.println("success!");
		}
		else {
			System.out.println("error!");
		}
	}
}

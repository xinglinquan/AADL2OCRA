package aadl2ocra.generator;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.osate.aadl2.SystemImplementation;

import aadl2ocra.utils.FileUtils;
import aadl2ocra.workflow.*;


public class Generation {
	public static IFolder ifolder = null;
    public static IProject projects;
	public static int codeGeneration(SystemImplementation system, IProject project, String destination) {
		// TODO Auto-generated method stub
		if (system != null && project != null)
		{
			//IFileSystemAccess2 fsa;
			FileUtils.projects = project;
//			testTime.testInit();
			GenerateOss.generate(system);
//			IFolder folder = project.getFolder("Ada_codes");
//			testTime.testEnd(folder);
			System.out.println("success111!");
			return 1;
		}
		System.out.println("failed!");
		return 0;
	}

}

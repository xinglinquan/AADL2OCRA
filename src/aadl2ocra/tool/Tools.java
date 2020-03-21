package aadl2ocra.tool;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;

public class Tools {
	public static IFolder ifolder = null;
    public static IProject projects;
    public static void folder(String folderName) {
		//IProject project = getCurrentProject();
		IProject project = projects;
		System.out.println("aaaaaaaa");
		IFolder systemFolder = project.getFolder(folderName);
		System.out.println("nbbbbb");
		if (!systemFolder.exists()) {
			try {
				systemFolder.create(true, true, null);
				ifolder = systemFolder;
			} catch (CoreException e) {
				// ... ...
				// Deal with Operation
			}
		} else {
			try {
				systemFolder.delete(true, null);
				systemFolder.create(true, true, null);
				ifolder = systemFolder;
			} 
			catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}
	public static void createFile(String folderName, String fileName, String in) {
		//	IProject project = getCurrentProject();
			IProject project = projects;
			System.out.println("project:"+projects);
			IFolder systemFolder = project.getFolder(folderName);
			System.out.println("nbbbbb");
			if (!systemFolder.exists()) {
				try {
					systemFolder.create(true, true, null);
					ifolder = systemFolder;
				} catch (CoreException e) {
					// ... ...
					// Deal with Operation
				}
			} else {
				try {
					systemFolder.delete(true, null);
					systemFolder.create(true, true, null);
					ifolder = systemFolder;
				} 
				catch (CoreException e) {
					e.printStackTrace();
				}
			}
			InputStream input = new ByteArrayInputStream(in.getBytes());
			IFile newFile = systemFolder.getFile(fileName);
			try {
				if (newFile.exists()) {
					newFile.setContents(input, true, false, null);
				} else {
					newFile.create(input, false, null);
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
}

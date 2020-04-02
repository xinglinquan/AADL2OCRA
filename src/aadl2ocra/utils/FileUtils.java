package aadl2ocra.utils;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Random;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;

public class FileUtils {
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
	
	public static File CmdFile(String folderName, String fileName, String in) {
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
			return newFile.getFullPath().toFile();
		}
	
	public static File CmdFile(File parentDir)
	{
		if( parentDir == null || !parentDir.isDirectory() )
		{
			System.out.println("parent directory is null or invalid");
			return null;
		}

		final Random randomGenerator = new Random();

		long value = randomGenerator.nextLong();
		while( value == Long.MIN_VALUE )
		{
			value = randomGenerator.nextLong();
		}

		final String fileName = "cmd_" + Long.toHexString(Math.abs(randomGenerator.nextLong())) + ".cmd" ;

		return new File(parentDir, fileName).getAbsoluteFile();
	}
}

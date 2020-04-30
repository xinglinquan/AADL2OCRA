package aadl2ocra.utils;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Random;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;

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
	
    public static String getPathFromClass(Class cls) throws IOException {
        String path = null;
        if (cls == null) {
            throw new NullPointerException();
        }
        URL url = getClassLocationURL(cls);
        if (url != null) {
            path = url.getPath();
            if ("jar".equalsIgnoreCase(url.getProtocol())) {
                try {
                    path = new URL(path).getPath();
                } catch (MalformedURLException e) {
                }
                int location = path.indexOf("!/");
                if (location != -1) {
                    path = path.substring(0, location);
                }
            }
            File file = new File(path);
            path = file.getCanonicalPath();
        }
        return path;
    }

    private static URL getClassLocationURL(final Class cls) {
        if (cls == null)
            throw new IllegalArgumentException("null input: cls");
        URL result = null;
        final String clsAsResource = cls.getName().replace('.', '/')
                .concat(".class");
        final ProtectionDomain pd = cls.getProtectionDomain();
        if (pd != null) {
            final CodeSource cs = pd.getCodeSource();
            if (cs != null)
                result = cs.getLocation();
            if (result != null) {
                if ("file".equals(result.getProtocol())) {
                    try {
                        if (result.toExternalForm().endsWith(".jar")
                                || result.toExternalForm().endsWith(".zip"))
                            result = new URL("jar:"
                                    .concat(result.toExternalForm())
                                    .concat("!/").concat(clsAsResource));
                        else if (new File(result.getFile()).isDirectory())
                            result = new URL(result, clsAsResource);
                    } catch (MalformedURLException ignore) {
                    }
                }
            }
        }
        if (result == null) {
            final ClassLoader clsLoader = cls.getClassLoader();
            result = clsLoader != null ? clsLoader.getResource(clsAsResource)
                    : ClassLoader.getSystemResource(clsAsResource);
        }
        return result;
    }
}

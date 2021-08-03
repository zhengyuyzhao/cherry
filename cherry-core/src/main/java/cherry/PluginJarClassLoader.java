package cherry;

import cherry.util.IoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 *
 * @author Ricky Fung
 * @create 2016-11-12 14:27
 */
public class PluginJarClassLoader {

	private final Logger logger = LoggerFactory.getLogger(PluginJarClassLoader.class);

	private URLClassLoader classLoader;

	public PluginJarClassLoader(String jarfile){
		this(new File(jarfile), null);
	}
	public PluginJarClassLoader(File jarfile){
		this(jarfile, null);
	}
	public PluginJarClassLoader(File jarfile, ClassLoader parent) {
		this.classLoader = createClassLoader(jarfile, parent);
	}

	private URLClassLoader createClassLoader(final File jarFile,
			ClassLoader parent) {
		if (null == parent) {
			parent = Thread.currentThread().getContextClassLoader();
		}
		if (null != jarFile && jarFile.canRead()) {
			URL[] elements = new URL[1];
			try {
				elements[0] = jarFile.toURI().normalize().toURL();
			} catch (MalformedURLException e) {
				logger.error("load jar file error", e);
			}
			return URLClassLoader.newInstance(elements, parent);
		}
		return null;
	}
	
	public Class<?> loadClass(String className) throws ClassNotFoundException{
		return classLoader.loadClass(className);
	}
}

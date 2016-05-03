package org.jdsnet.testboxrunner;

import java.io.File;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

public class TestBoxSuiteRunnerService {
	
	private static final String DEFAULT_TEST_SOURCE = "src/test/cfml";
	private static final Logger LOGGER = Logger.getLogger(TestBoxSuiteRunnerService.class.getName());
	
	public static String getFormattedPath(final URL url) {
		final String urlPath = url.getPath();
		
		String formatted;
		
		if (urlPath.length() > 0 && urlPath.startsWith("/")) {
			formatted = urlPath.substring(1);
		} else {
			formatted = urlPath;
		}
		
		return formatted;
	}
	
	public static List<URL> scanTestFiles() {
		return new ArrayList<>();
	}

	public static String getSourceDirectory(Class<?> testClass) {
		TestBox.SourceDir sourceDirAnnotated = testClass.getAnnotation(TestBox.SourceDir.class);
		if (sourceDirAnnotated != null) {
			return sourceDirAnnotated.value();
		} else {
			return DEFAULT_TEST_SOURCE;
		}
	}
	
	public static boolean portAvailable(int port) {
		ServerSocket ss = null;
		DatagramSocket ds = null;
		try {
			ss = new ServerSocket(port);
			ss.setReuseAddress(true);
			ds = new DatagramSocket(port);
			ds.setReuseAddress(true);
			return true;
		} catch (IOException e) {
		} finally {
			if (ds != null) {
				ds.close();
			}

			if (ss != null) {
				try {
					ss.close();
				} catch (IOException e) {
					/* should not be thrown */
				}
			}
		}

		return false;
	}
	
	
	private File testSourceDir;
	private Tomcat tomcat = new Tomcat();
	private int port;
	
	public TestBoxSuiteRunnerService(File testSourceDir) throws Exception {
		this.testSourceDir = testSourceDir;
		port = discoverPort();
		tomcat.setPort(port);
		
		File contextRoot = new File("target/test-runtime");
		
		tomcat.addWebapp("/", contextRoot.getAbsolutePath());
		
		
		
		tomcat.start();
		tomcat.getServer().await();
	}
	
	public void shutdown() throws Exception {
		tomcat.stop();
	}
	
	private int discoverPort() {
		int portMin = 30000;
		int portMax = 49999;
		int port = new Random().nextInt((portMax - portMin)+1) + portMin;
		
		if (!portAvailable(port))
			return discoverPort();
		else
			return port;
	}
}

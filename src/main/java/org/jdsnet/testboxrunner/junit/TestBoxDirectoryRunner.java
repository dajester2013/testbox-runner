package org.jdsnet.testboxrunner.junit;

import java.io.File;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.plexus.util.DirectoryScanner;
import org.jdsnet.testboxrunner.TestBoxSuiteRunnerService;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;

public class TestBoxDirectoryRunner extends ParentRunner<URL> {

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	@Inherited
	public @interface Directory {
		String value();
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	@Inherited
	public @interface Recurse {
		boolean value() default true;
	}
	
	public TestBoxDirectoryRunner(Class<?> testClass) throws InitializationError {
		super(testClass);
		
		String testSourcePath = TestBoxSuiteRunnerService.getSourceDirectory(testClass);
		String testDirPath = "";
		
		Directory directory = testClass.getAnnotation(Directory.class);
		if (directory != null) {
			testDirPath = directory.value();
		}
		
		File testSourceDirectory = new File(testSourcePath);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Description describeChild(URL childUrl) {
		System.out.print("dc::");
		System.out.println(childUrl.toString());
		// TODO Auto-generated method stub
		return Description.createTestDescription(this.getTestClass().getJavaClass(), TestBoxSuiteRunnerService.getFormattedPath(childUrl));
	}

	@Override
	protected List<URL> getChildren() {
		System.out.print("gc::");
		System.out.println();
		// TODO Auto-generated method stub
		return new ArrayList<URL>();
	}

	@Override
	protected void runChild(URL childUrl, RunNotifier notifier) {
		System.out.println(childUrl.toString());
		System.out.println(notifier.toString());
	}
	
}

package org.jdsnet.testboxrunner.junit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.URL;
import java.util.List;

import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;

public class TestBoxDirectoryRunner extends ParentRunner<URL> {

	protected TestBoxDirectoryRunner(Class<?> testClass) throws InitializationError {
		super(testClass);
		// TODO Auto-generated constructor stub
	}

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

	@Override
	protected Description describeChild(URL arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<URL> getChildren() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void runChild(URL arg0, RunNotifier arg1) {
		// TODO Auto-generated method stub
		
	}
	
}

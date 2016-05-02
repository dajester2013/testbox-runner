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

public class TestBoxBundleRunner extends ParentRunner<URL> {

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	@Inherited
	public @interface Bundles {
		String[] value();
	}

	
	
	protected TestBoxBundleRunner(Class<?> testClass) throws InitializationError {
		super(testClass);
		// TODO Auto-generated constructor stub
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

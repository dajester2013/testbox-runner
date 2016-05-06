package org.jdsnet.testboxrunner;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface TestBox {

	public @interface SourceFolder {
		public enum Type { COMPONENT, MAPPED }
		
		Type type() default Type.COMPONENT;
		String physical();
		String virtual() default "";
	}
	
	SourceFolder[]	sources();
	String[]		labels()	default {};
	String			reporter()	default "";

	
}

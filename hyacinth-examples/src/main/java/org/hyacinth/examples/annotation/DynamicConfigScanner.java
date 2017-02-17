package org.hyacinth.examples.annotation;

import java.io.IOException;

import sun.reflect.annotation.AnnotationType;

import com.google.common.reflect.ClassPath;

public class DynamicConfigScanner {
	private String basePackage = "com.jd.hyacinth";
	final ClassLoader loader = Thread.currentThread().getContextClassLoader();
	public DynamicConfigScanner() {
		try {
			System.out.println(loader);
			for (final ClassPath.ClassInfo info : ClassPath.from(loader).getTopLevelClasses()) {
				  if (info.getName().startsWith(basePackage)) {
				    final Class<?> clazz = info.load();
				    Config config = clazz.getAnnotation(Config.class);
                    if (null != config) {
                        System.out.println(clazz.getName());
                    }
				  }
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

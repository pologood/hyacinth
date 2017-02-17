package org.hyacinth.examples.annotation;

import java.lang.annotation.*;

/**
 * 配置在 属性，或方法上，标识绑定的配置信息名称
 */
@Target({ElementType.TYPE,ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Config {
	/** 配置类型 */
	String type() default "common";
	
	/** 配置名称 */
	String value();
	
	/** 缓存时间（秒）  默认5分钟 */
	int cacheTime() default 300;

}

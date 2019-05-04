package com.zhou.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)//用于限定当前自定义注解类左右的对象
//@Retention(RetentionPolicy.CLASS)//该注解类会被编译到字节码中，但是当虚拟机去加载这个字节码的时候，注解信息就被清除
//@Retention(RetentionPolicy.SOURCE) //该注解类只会在源码中出现，当将源码编译成字节码的时候注解信息就被清除
@Retention(RetentionPolicy.RUNTIME)//该注解类一直保留到被加载到虚拟机中
public @interface ViewInject {
	String id();
	String name();
	int age();
}

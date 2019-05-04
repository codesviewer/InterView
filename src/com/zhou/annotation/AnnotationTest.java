package com.zhou.annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationTest {

	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		//需求：获取User类中name字段上的自定义注解的值，然后将该值的age通过反射设置给User对象的age属性，将name设置给User对象的name属性。
		
		User user = new User();
		
		/**
		 *1.获取到User类的字节码  :三种方式
		 * user.getClass()
		 * User.class;
		 * Class.forName("");传入类的全名
		 */
		Class clazz = User.class;
		
		/**
		 * 2.将字节码中的成员变量字段获取到
		 * clazz.getField(String name)//只能获取声明为public的字段
		 * clazz.getDeclaredField(String name)//只能获取所有的字段，包括private修饰的
		 */
		Field idField = clazz.getDeclaredField("id");
		Field nameField = clazz.getDeclaredField("name");
		Field ageField = clazz.getDeclaredField("age");
		
		/**
		 * 3.将当前字段上的注解对象获取到
		 */
		//必须用这个idField而不能用nameField,ageField,应该注解是写道第一个是String上面的，如果注解第一个是name,那么就需要写nameField
		ViewInject viewInject = idField.getAnnotation(ViewInject.class); 
		if (viewInject != null) {
			/**
			 * 4.获取自定义注解对象中的参数
			 */
			String name = viewInject.name();
			String id = viewInject.id();
			int age = viewInject.age();
			System.out.println("id = " + id + ",name=" + name + ",age = " + age);
			
			/**
			 * 5.通过反射将三个值设置给User对象
			 */
			nameField.setAccessible(true);//设置允许访问，其实就是允许暴力反射
			nameField.set(user, name);//用nameField给user对象的name设置值为从注解中拿出来的name
			
			idField.setAccessible(true);//设置允许访问，其实就是允许暴力反射
			idField.set(user, id);//用idField给user对象的id设置值为从注解中拿出来的id
			
			ageField.setAccessible(true);//设置允许访问，其实就是允许暴力反射
			ageField.set(user, age);//用ageField给user对象的age设置值为从注解中拿出来的age
			
			System.out.println(user.toString());
		}else {
			System.out.println("字段上面没有自定义注解");
		}
		
		//通过反射调用User的成员方法
		System.out.println("通过反射调用User的成员方法::");
		Method eatMethod = clazz.getDeclaredMethod("eat", String.class);
		//暴力反射调用该方法
		eatMethod.setAccessible(true);//必须加上这句代码
		Object result = eatMethod.invoke(user, "牛肉拉面");
		System.out.println(result);
		
		System.out.println("hotfix");
	}

}

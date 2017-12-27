/**
* Title: ReflectionTest.java
* Description: 测试反射都有哪些操作。
* Copyright: Copyright (c) 2017
* Company: TongjiUniversity
* @author mdm(computer in lab)
* @date 2017年12月25日
* @version 1.0
*/
package com.tongji.dynamicproxy;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.junit.Test;

/**  
* Title: ReflectionTest 
* Description:  熟悉反射的操作。
* @author mdm(computer in lab)  
* @date 2017年12月25日  
*/
public class ReflectionTest {

	//关于类的加载器：ClassLoader
	@Test
	public void test5() throws Exception{
		ClassLoader loader1 = ClassLoader.getSystemClassLoader();
		System.out.println(loader1);
		
		ClassLoader loader2 = loader1.getParent();
		System.out.println(loader2);
		
		ClassLoader loader3 = loader2.getParent();
		System.out.println(loader3);
		
		Class clazz1 = Person.class;
		ClassLoader loader4 = clazz1.getClassLoader();
		System.out.println(loader4);
		
		String className = "java.lang.String";
		Class clazz2 = Class.forName(className);
		ClassLoader loader5 = clazz2.getClassLoader();
		System.out.println(loader5);
		
		//掌握如下
		//法一：
		ClassLoader loader = this.getClass().getClassLoader();
		InputStream is = loader.getResourceAsStream("com\\atguigu\\java\\jdbc.properties");
		//法二：如果文件就在当前工程下 
//		FileInputStream is = new FileInputStream(new File("jdbc1.properties"));
		
		Properties pros = new Properties();
		pros.load(is);
		String name = pros.getProperty("user");
		System.out.println(name);
		
		String password = pros.getProperty("password");
		System.out.println(password);
		
	}
	//如何获取Class的实例（3种）
	@Test
	public void test4() throws ClassNotFoundException{
	//1.调用运行时类本身的.class属性
		Class clazz1 = Person.class;
		System.out.println(clazz1.getName()); //执行到这，类还没被加载
		
	//2.通过运行时类的对象获取，每个人都有的，因为是object里面的方法
		Person p = new Person();
		Class clazz3 = p.getClass();
		System.out.println(clazz3.getName());
		
	//3.通过Class的静态方法获取.通过此方式，体会一下，反射的动态性。
		String className = "com.tongji.dynamicproxy.Person";
		Class clazz4 = Class.forName(className); //执行此句的时候类就加载了
		//clazz4.newInstance();
		System.out.println(clazz4.getName());
		
	//4.（了解）通过类的加载器
		ClassLoader classLoader = this.getClass().getClassLoader();
		Class clazz5 = classLoader.loadClass(className);
		System.out.println(clazz5.getName());
		
		System.out.println(clazz1 == clazz3);//true
		System.out.println(clazz1 == clazz4);//true
		System.out.println(clazz1 == clazz5);//true
	}
	
	/*
	 * java.lang.Class:是反射的源头。
	 * 我们创建了一个类，通过编译（javac.exe）,生成对应的.class文件。之后我们使用java.exe加载（JVM的类加载器完成的）
	 * 此.class文件 ，此.class文件加载到内存以后，就是一个运行时类，存在在缓存区。那么这个运行时类本身就是一个Class的实例！
	 * 1.每一个运行时类只加载一次！
	 * 2.有了Class的实例以后，我们才可以进行如下的操作：
	 *     1）*创建对应的运行时类的对象
	 *     2）获取对应的运行时类的完整结构（属性、方法、构造器、内部类、父类、所在的包、异常、注解、...）
	 *     3）*调用对应的运行时类的指定的结构(属性、方法、构造器)
	 *     4）反射的应用：动态代理
	 */
	@Test
	public void test3(){
		Person p = new Person();
		Class clazz = p.getClass();//通过运行时类的对象，调用其getClass()，返回其运行时类。
		System.out.println(clazz);
	}
	
	/**
	 * 功能：有了反射以后我们的使用方式，我们新建对象的方式等都发生了变化
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws NoSuchMethodException 
	 *
	 */
	@Test
	public void TestReflection() throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		Class<Person> clazz= Person.class;//可以理解为引用指向对象,此时还未被加载
	
		//1.创建吹clazz对应的运行时类Person类的对象
		Person person =clazz.newInstance();
		//这种方式只能去使用public声明的变量
		Field field=clazz.getField("name");
		field.set(person, "wanglifeng");
		System.out.println(person);
		
		//2.获取和修改私有的属性
		Field field2=clazz.getDeclaredField("age");
		field2.setAccessible(true);
		field2.set(person, 20);
		System.out.println(person);
		
		//3.通过反射来调用运行时类的指定方法
		Method method=clazz.getMethod("display", String.class);
		method.invoke(person, "USA");
	}
	/**
	 * 功能：没有反射以前我们的做法。我们都是新建一个类的对象，然后去调用方法和设置属性
	 * @throws Exception 
	 *
	 */
	@Test
	public void BeforReflection() throws Exception {
		Person person= new Person();
		person.setAge(10);
		person.setName("tangwei");
		System.out.println(person);
		person.show();
		person.display("HK");			
	}
}

/**
* Title: Person.java
* Description: 这是一个测试反射用的类。 
* Copyright: Copyright (c) 2017
* Company: TongjiUniversity
* @author mdm(computer in lab)
* @date 2017年12月25日
* @version 1.0
*/
package com.tongji.dynamicproxy;

/**  
* Title: Person 
* Description: 这是一个测试反射用的类。 
* @author mdm(computer in lab)  
* @date 2017年12月25日  
*/
@Myannotation(value="tongji")
public class Person extends Creature<String> implements Comparable,Myinterface {
	public String name;
	private int age;
	static {
		System.out.println("Person类被加载了");
	}
	//创建类的时候，尽量给他指定一个空参的构造器
	public Person() {
		super();
		System.out.println("我是空参构造器");
	}
	public Person(String name) {
		super();
		this.name = name;
	}
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Myannotation(value="abc123")
	public void show() {
		System.out.println("我是一个人");
	}
	public void display(String nation) throws Exception{
		System.out.println("我的国籍是"+nation);
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	/* （非 Javadoc）
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Object o) {
		// TODO 自动生成的方法存根
		return 0;
	}
	class Bird{
		
	}
	
}

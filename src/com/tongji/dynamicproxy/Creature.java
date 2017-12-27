/**
* Title: Creature.java
* Description: 
* Copyright: Copyright (c) 2017
* Company: TongjiUniversity
* @author mdm(computer in lab)
* @date 2017年12月26日
* @version 1.0
*/
package com.tongji.dynamicproxy;

/**  
* Title: Creature 
* Description:  这是一个Person的父类，用于测试继承后能不能获得父类
* @author mdm(computer in lab)  
* @date 2017年12月26日  
*/
public class Creature<T> {

	double weight;
	public void breath() {
		System.out.println("呼吸！");
	}
}

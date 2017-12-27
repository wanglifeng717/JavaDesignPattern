/**
* Title: test.java
* Description: 这是一个动态代理的例子。首先一个借口，然后MyInvocationHandler实现了接口实现InvocationHandler接口
* 然后返回一个代理类的对象，之后我们调用用代理类调用
* Copyright: Copyright (c) 2017
* Company: TongjiUniversity
* @author mdm(computer in lab)
* @date 2017年12月25日
* @version 1.0
*/
package com.tongji.dynamicproxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//动态代理的使用，体会反射是动态语言的关键
interface Subject {
	void action();
}

// 被代理类
class RealSubject implements Subject {
	public void action() {
		System.out.println("我是被代理类，记得要执行我哦！么么~~");
	}
}

class MyInvocationHandler implements InvocationHandler {
	Object obj;// 实现了接口的被代理类的对象的声明

	// ①给被代理的对象实例化②返回一个代理类的对象
	public Object blind(Object obj) {
		this.obj = obj;
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj
				.getClass().getInterfaces(), this);
	}
	//当通过代理类的对象发起对被重写的方法的调用时，都会转换为对如下的invoke方法的调用
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		//method方法的返回值时returnVal
		System.out.println("invoke");
		Object returnVal = method.invoke(obj, args);
		return returnVal;
	} 

}

public class TestDynamicProxy {
	public static void main(String[] args) {
		//1.被代理类的对象
		RealSubject real = new RealSubject();
		//2.创建一个实现了InvacationHandler接口的类的对象
		MyInvocationHandler handler = new MyInvocationHandler();
		//3.调用blind()方法，动态的返回一个同样实现了real所在类实现的接口Subject的代理类的对象。
		Object obj = handler.blind(real);
		System.out.println(obj.getClass().getName());
		Subject sub = (Subject)obj;//此时sub就是代理类的对象
		//这里就是用代理类去调用接口里面的方法，然后自动转为调用invoke方法
		sub.action();//转到对InvacationHandler接口的实现类的invoke()方法的调用
		
		//再举一例
		/*NikeClothFactory nike = new NikeClothFactory();
		ClothFactory proxyCloth = (ClothFactory)handler.blind(nike);//proxyCloth即为代理类的对象
		proxyCloth.productCloth();*/
		
		
		
	}
}

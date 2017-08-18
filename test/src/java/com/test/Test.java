package com.test;

import java.util.ArrayList;
import java.util.HashMap;

public class Test {

	
	
	public static void main(String[] args) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("1", "1");
		map.put("2", "2");
		map.put("3", "3");
		HashMap<String, String> newMap = (HashMap<String, String>) (map.clone());
		System.out.println(map);
		System.out.println(newMap);
		
		System.out.println(map.clone() == map);
		System.out.println(map.clone().getClass() == map.getClass());
		System.out.println(map.clone().equals(map));
		
	}
	
	public static void test1() {
		System.out.println(1111111);
		
		Thread thread = Thread.currentThread();
		System.out.println(thread.getId());
		System.out.println(thread.getName());
	}
	
	public static void test2() {
		
		Thread thread = Thread.currentThread();
		System.out.println(thread.getId());
		System.out.println(thread.getName());
		
		Thread newThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				Thread thread = Thread.currentThread();
				System.out.println(thread.getId());
				System.out.println(thread.getName());
			}
		});
		
		newThread.start();
	}
	
	
	public static void test3() {
		Cloneable clone;
	}

}

abstract class A{
	abstract int geta();
	abstract public double getb();
	abstract int getc();
}

class B extends A{
	int geta() {
		return 0;
		
	}
	public double getb() {
		return 0;
	}
	int getc() {
		return 0;
	}
}

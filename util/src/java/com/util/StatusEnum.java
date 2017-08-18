package com.util;

/**
 * 状态枚举
 */
public class StatusEnum {
	
	public enum Operation{insert, toInsert, delete, update, toUpdate, select};//操作类型
	public enum UserRole{web, admin};//系统用户角色
	
	
	
	
	
	
	public static void main(String[] args) {
		// toString之后与其名字对应的字符串相等
		System.out.println("select".equals(Operation.select.toString()));//true
		
		Enum<?> operation = Operation.select;
		System.out.println(operation == Operation.select);//true
		System.out.println(operation.equals(Operation.select));//true
		System.out.println(operation.equals(Operation.select.toString()));//false
		
		// 字符串转换成指定Enum
		Operation opEnum = Enum.valueOf(Operation.class, "select");
		System.out.println(opEnum);
		System.out.println(opEnum == Operation.select);//true
		System.out.println(opEnum.equals(Operation.select));//true
		System.out.println(opEnum.equals(Operation.select.toString()));//false
	}
}
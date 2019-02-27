package com.jankin.springboot.demo.test;

import java.lang.reflect.Field;

/**
 * 测试类
 * */
public class AnnotationDemo{
	public static void main(String[] args) {
		getPersonInfo(Person.class);
	}
	public static void getPersonInfo(Class<?> clazz){
		String address = "地址";
		String chart = "性格";
		String card = "证件信息";
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			//判断该属性注解种类名称
			if(field.isAnnotationPresent(Address.class)){
				Address addre = field.getAnnotation(Address.class);
				address = address+":"+addre.value();
				System.out.println(address);
			}else if(field.isAnnotationPresent(Character.class)){
				Character chara = field.getAnnotation(Character.class);
				chart = chart+":"+chara.personChart();
				System.out.println(chart);
			}else if(field.isAnnotationPresent(Card.class)){
				Card cad = field.getAnnotation(Card.class);
				card = card+":id"+cad.id()+";name"+cad.name()+";num:"+cad.num();
				System.out.println(card);
			}
		}
		
	}
}
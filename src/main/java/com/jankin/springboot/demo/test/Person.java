package com.jankin.springboot.demo.test;

public class Person {
	@Address("黑龙江省哈尔滨市")
	String myAddress;
	@Character(personChart = Character.Chart.CRAZY)
	String myCharactor;
	@Card(id=25,name="Wizard",num="4428")
	String myCard;
}
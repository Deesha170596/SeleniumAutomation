package com.qa.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.baseClass.BaseClass;
@Test(groups= {"AllClssTests"})
public class TestNGGroups extends BaseClass {
	@BeforeMethod
	public void setup()
	{
		initialization();
	}
	@Test(groups= {"sanity"})
	public void test1() {
		System.out.println("This is test1");
	}
	@Test(groups= {"sanity","smoke"})
	public void test2() {
		System.out.println("This is test2");
	}
	@Test(groups= {"sanity","regression"})
	public void test3() {
		System.out.println("This is test3");
	}
	@AfterMethod
	public void tesrdown()
	{
		teardown();
	}
}

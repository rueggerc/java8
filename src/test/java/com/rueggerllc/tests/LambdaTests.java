package com.rueggerllc.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.rueggerllc.beans.BusinessObject;
import com.rueggerllc.beans.Foo;
import com.rueggerllc.beans.Node;
import com.rueggerllc.functions.MyFunctionInterface;

public class LambdaTests {

	private static Logger logger = Logger.getLogger(TestCommonElementsInSortedArrays.class);
	
	
	
	@BeforeClass
	public static void setupClass() throws Exception {
	}
	
	@AfterClass
	public static void tearDownClass() throws Exception {
	}

	@Before
	public void setupTest() throws Exception {
	}

	@After
	public void tearDownTest() throws Exception {
	}
	
	
	@Test
	@Ignore
	public void testLambda() {
		try {
			BusinessObject businessObject = new BusinessObject();
			businessObject.execute(new MyFunction());
			
			businessObject.execute(() -> {System.out.println("Lamba MyFunction Object here!");});
			
			MyFunctionInterface foo = () -> {System.out.println("MyFunction as separate object");};
			businessObject.execute(foo);
			
		} catch (Exception e) {
			logger.error("ERROR", e);
		}
	}
	
	public static class MyFunction implements MyFunctionInterface {
		@Override
		public void doSomeWork() {
			logger.info("DOING SOME WORK HERE!");
		}
	}
		
	
}

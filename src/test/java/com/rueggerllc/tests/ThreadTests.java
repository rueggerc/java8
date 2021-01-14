package com.rueggerllc.tests;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.rueggerllc.threads.diners.DiningApp;

public class ThreadTests {

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
	public void dummyTest() {
	}
	
	
	@Test
	public void testDiningPhilosophers() throws Exception {
		DiningApp diningApp = new DiningApp();
		diningApp.execute();
	}

	

	
	
}

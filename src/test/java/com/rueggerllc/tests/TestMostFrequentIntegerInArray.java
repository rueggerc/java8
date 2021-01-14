package com.rueggerllc.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class TestMostFrequentIntegerInArray {

	private static Logger logger = Logger.getLogger(TestMostFrequentIntegerInArray.class);
	
	
	
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
		logger.info("Inside Dummy Test");
	}
	
	
	@Test
	@Ignore
	public void testAnagrams() {
	}
	
	@Test
	// @Ignore
	public void testMostFrequentIntegerInArray() {
		int[] data  = new int[] {1, 3, 1, 3, 2, 1};
		Integer most = getMostFrequent(data);
		logger.info("Most Frequent=" + most);
		
		data  = new int[] {7,5,3,9,9,9,8,7,4,6,7,2,1,1,7};
		most = getMostFrequent(data);
		logger.info("Most Frequent=" + most);
		
	}
	
	private Integer getMostFrequent(int[] data) {
		if (data == null || data.length == 0) {
			return null;
		}
		
		Map<Integer,Integer> lookup = new HashMap<Integer,Integer>();
		Integer most = null;
		int highestCount = 0;
		for (int i = 0; i < data.length; i++) {
			int key = data[i];
			Integer counter = lookup.get(key);
			if (counter == null) {
				counter = new Integer(1);
			} else {
				counter = new Integer(counter.intValue() + 1);
			}
			lookup.put(key, counter);
			
			if (counter.intValue() > highestCount) {
				highestCount = counter;
				most = key;
			}
		}
		
		// Done
		logger.info("Most=" + most + ":" + highestCount);
		return most;
		
	}
	
	
	
	
	
}

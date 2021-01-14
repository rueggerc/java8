package com.rueggerllc.tests;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class TestCommonElementsInSortedArrays {

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
		logger.info("Inside Dummy Test");
	}
	
	
	@Test
	@Ignore
	public void testAnagrams() {
	}
	
	@Test
	@Ignore
	public void testCommonElementsSortedArrays() {
		int[] arrayA = new int[] {1,3,4,6,7,9};
		int[] arrayB = new int[] {1,2,4,5,9,10};
		// Should Return 1,4,9
		Integer[] common = getCommonElements(arrayA, arrayB);
		if (common == null) {
			return;
		}
		for (Integer next : common) {
			logger.info("Next=" + next);
		}
	}
	
	private Integer[] getCommonElements(int[] arrayA, int[] arrayB) {
		if (arrayA == null || arrayA.length == 0){
			return null;
		}
		if (arrayB == null || arrayB.length == 0) {
			return null;
		}
		List<Integer> common = new ArrayList<Integer>();
		int posA = 0;
		int posB = 0;
		do {
			int nextA = arrayA[posA];
			int nextB = arrayB[posB];
			if (nextA == nextB) {
				logger.info("Found Match=" + nextA);
				common.add(nextA);
				posA++;
				posB++;
			} else if (nextA > nextB) {
				posB++;
			} else if (nextA < nextB) {
				posA++;
			}

		} while (posA <= arrayA.length && posB <= arrayB.length);
		
		// Done
		return common.toArray(new Integer[common.size()]);
	}
	
	
	
	
	
}

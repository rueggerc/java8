package com.rueggerllc.tests;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.rueggerllc.qsort.QuickSort;

public class SortTests {

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
	public void testQuickSort() {
		int[] values = new int[] {10,80,30,90,40,50,70};
		QuickSort quickSort = new QuickSort();
		quickSort.sort(values);
		dump(values);
	}
	
	private void dump(int[] array) {
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			buffer.append(array[i] + " ");
		}
		System.out.println(buffer.toString());
	}	
	

	
	
}

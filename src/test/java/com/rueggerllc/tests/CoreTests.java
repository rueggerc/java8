package com.rueggerllc.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
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

import myqueue.MyQueue;

public class CoreTests {

	private static Logger logger = Logger.getLogger(CoreTests.class);
	
	
	
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
	public void testMyQueue() throws Exception {
		MyQueue myqueue = new MyQueue();
		myqueue.add(1);
		myqueue.add(2);
		myqueue.add(3);
		
		int x = myqueue.remove();
		System.out.println("x=" + x);
		
		myqueue.add(4);
		myqueue.add(5);
		
		x = myqueue.remove();
		System.out.println("x=" + x);
		
		x = myqueue.remove();
		System.out.println("x=" + x);
		
		x = myqueue.remove();
		System.out.println("x=" + x);
		
		x = myqueue.remove();
		System.out.println("x=" + x);
		
		
	}
	
	@Test
	public void testQueue() {
		try {
			Queue<String> queue = new LinkedList<String>();
			queue.add("Foo");
			queue.add("bar");
		} catch (Exception e) {
			logger.error("ERROR", e);
		}
	}
	
	@Test
	public void testStack() {
		try {
			Stack<String> stack = new Stack();
			stack.push("Foo");
			stack.push("Bar");
			
			String top = stack.pop();
			logger.info("popped=" + top);
			logger.info("TOP=" + stack);

		} catch (Exception e) {
			logger.error("ERROR", e);
		}
	}
	
	@Test
	@Ignore
	public void findSmallestAndSecondSmallest() {
		int theList[] = {12,13,1,10,34,1};
		
		int smallest = Integer.MAX_VALUE;
		int second = Integer.MAX_VALUE;
		for (int next : theList) {
			if (next < smallest) {
				second = smallest;
				smallest = next;
			} else if (next < second && next != smallest) {
				second = next;
			}
		}
		System.out.println("Smallest=" + smallest);
		System.out.println("Second=" + second);
		
	}
	
	@Test
	@Ignore
	public void testPriorityQueue() {
		PriorityQueue<Integer> pQueue = new PriorityQueue<>();
		pQueue.add(14);
		pQueue.add(11);
		pQueue.add(3);
		pQueue.add(55);
		pQueue.add(23);
		
		System.out.println("pQueue=" + pQueue);
		while(!pQueue.isEmpty()) {
			System.out.println(pQueue.remove());
		}
		
	}
	
	@Test
	@Ignore
	public void testFactorial() {
		Map<Integer,Integer> lookup = new HashMap<>();
		lookup.put(1, 1);
		System.out.println("Factorial=" + factorial(5,lookup));
	}
	
	private int factorial(int n,Map<Integer,Integer> lookup) {
		Integer preCompute = lookup.get(n);
		System.out.println("Precompute for " + n + "=" + preCompute);
		if (preCompute != null) {
			return preCompute;
		}
		int result = n * factorial(n-1,lookup);
		System.out.println("Store for n=" + n + "=" + result);
		lookup.put(n, result);
		return result;
	}
	
	
	@Test
	@Ignore
	public void testPalindrome() {
		System.out.println("Palindrome");
		String input = "bananas";
		
		int start = 0;
		int maxLength = 1;
		
		int n = input.length();
		boolean table[][] = new boolean[n][n];
		
		// Initialize substrings of length 1 = true
		for (int i = 0; i < n; i++) {
			table[i][i] = true;
		}
		
		// Substrings of size 2
		for (int i = 0; i < n-1; i++) {
			if (input.charAt(i) == input.charAt(i+1)) {
				table[i][i+1] = true;
				start = i;
				maxLength = 2;
			}
		}
		
		// Substrings size 3 to N
		for (int k = 3; k <= n; k++) {
			
			for (int j = 0; j < (n-k+1); j++) {
				// Ending Index
				int x = j + k -1;
				if (input.charAt(j) == input.charAt(x) && table[j+1][x-1] == true) {
				  table[j][x] = true;
				  if (k > maxLength) {
					  maxLength = k;
					  start = j;
					  System.out.println("New Longest=" + input.substring(j, j+k));
				  }
				}
			}
		}
		
		// Done
		System.out.println("MaxLength=" + maxLength);
	
	}
	
	
	
	@Test
	@Ignore
	public void removeDuplicateCharacters0() {
		String test = "abakkccdefcjgks";
		String result = "";
		char[] theArray = test.toCharArray();
		for (int i = 0; i < theArray.length; i++) {
			if (result.indexOf(theArray[i]) == -1) {
				result += theArray[i];
			}
		}
		System.out.println("Result0=" + result);
	}
	
	@Test
	@Ignore
	public void removeDuplicateCharacters1() {
		String test = "abakkccdefcjgks";
		char[] theArray = test.toCharArray();
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < theArray.length; i++) {
			int j = 0;
			for (j = 0; j < i; j++) {
				if (theArray[j] == theArray[i]) {
					break;
				}
			}
			if (j == i) {
				buffer.append(theArray[i]);
			}
		}
		System.out.println("Result1=" + buffer.toString());
	}
	
	@Test
	@Ignore
	public void removeDuplicateCharacters2() {
		String test = "abakkccdefcjgks";
		char[] theArray = test.toCharArray();
		Set<Character> charSet = new HashSet<>();
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < theArray.length; i++) {
			if (!charSet.contains(theArray[i])) {
				buffer.append(theArray[i]);
				charSet.add(theArray[i]);
			}
		}
		System.out.println("Result2=" + buffer.toString());
	}
	
	@Test
	@Ignore
	// @Ignore
	// https://www.geeksforgeeks.org/reverse-a-string-in-java/
	public void testReverseString1() {
		System.out.println("Reverse String1");
		String foo = "Fred";
		char[] temp = foo.toCharArray();
		char[] bar = new char[temp.length];
		int pos=0;
		for (int i = temp.length-1; i>=0; i--,pos++) {
			bar[pos] = temp[i];
		}
		String reverse = String.valueOf(bar);
		System.out.println("reverse=" + reverse);
	}
	
	@Test
	@Ignore
	public void testFizzBuzz1() {
		for (int i = 1; i <= 100; i++) {
			if ((i%3==0) && (i%5==0)) {
				System.out.println("FizzBuzz: " + i);
			} else if (i%3==0) {
				System.out.println("Fizz: " + i);
			} else if (i%5 ==0) {
				System.out.println("Buzz: " + i);
			} else {
				System.out.println(i);
			}
		}
	}
	
	@Test
	// https://stackoverflow.com/questions/4989091/removing-duplicates-from-a-string-in-java
	@Ignore
	public void testRemoveDuplicatesFromString() {
		String foo = "abaccdefcjgks";
	}
	
	@Test
	@Ignore
	public void findCommonIntegers1() {
		System.out.println("FindCommon1");
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(42);
		list1.add(300);
		list1.add(25);
		
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(300);
		list2.add(11);
		list2.add(66);
		
		list1.retainAll(list2);
		for (Integer x : list1) {
			System.out.println("COMMON1=" + x);
		}
		
		// Collections.sort(list1);
		// Collections.sort(list2);		
	}
	
	@Test
	@Ignore
	public void findCommonIntegers2() {
		System.out.println("FindCommon2");
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(42);
		list1.add(300);
		list1.add(25);
		
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(300);
		list2.add(11);
		list2.add(66);
	
		List<Integer> common = new ArrayList<>();
		for (Integer x : list1) {
			if (list2.contains(x)) {
				common.add(x);
			}
		}
		
		System.out.println("COMMON2");
		for (Integer x : common) {
			System.out.println("COMMON2=" + x);
		}
		
		
		
	}
	
	@Test
	@Ignore
	public void testSecondHighest() {
		System.out.println("=== GET HIGHEST");
		int[] theNumbers = new int[] {18,12,49,66,2,5,31};
		int highest = Integer.MIN_VALUE;
		int second = Integer.MIN_VALUE;
		for (int next : theNumbers) {
			if (next > highest) {
				second = highest;
				highest = next;
			} else if (next > second) {
				second = next;
			}
		}
		System.out.println("Second=" + second);
	}
	
	@Test
	@Ignore
	public void testFibonacci() {
		System.out.println("Get Fibonacci");
		// 0, 1, 1, 2, 3, 5, 8, 13
		System.out.println("Fib(5)=" + getFib(5));
	}
	
	public int getFib(int pos) {
		if (pos == 1) {
			return 0;
		} else if (pos == 2) {
			return 1;
		} else {
			return getFib(pos-1) + getFib(pos-2);
		}
	}
	
	@Test
	@Ignore
	public void testSortLis() {
		List<Integer> myList = new ArrayList<Integer>();
		myList.add(42);
		myList.add(3);
		myList.add(11);
		myList.add(0);
		Collections.sort(myList);
		for (Integer x : myList) {
			System.out.println("Next=" + x);
		}
	}
	
	@Test
	@Ignore
	public void testReverseString() {
		System.out.println("Reverse String");
		StringBuilder buffer = new StringBuilder();
		String foo = "fred";
		for (int i = foo.length()-1; i >= 0; i--) {
			buffer.append(foo.charAt(i));
		}
		System.out.println(buffer.toString());
	}
	
	@Test
	@Ignore
	public void getDigits() {
		int foo = 4295;
		while (foo > 0) {
			int digit = foo % 10;
			foo = foo / 10;
			System.out.println(digit);
		}
	}
	
	@Test
	@Ignore
	public void testUnicode() {
		
		String foo = "\u0014";
		String bar = "\u00a5";
		String x = "\u0041";
		System.out.println("foo=" + foo);
		System.out.println("bar=" + bar);
		System.out.println("x=" + x);
		
		// String sensorData = "\b�\u0002\u0012\u0007Sensor1\u001a\u000572.54\"\u000583.09";
		String sensorData = "\u0002\u0012\u0007Sensor1\u001a\u000572.54\"\u000583.09";
		System.out.println("Sensor Data=" + sensorData);
		
		
	}
	
	@Test
	@Ignore
	public void reverseDigits() {
		System.out.println("Reverse BEGIN");
		int foo = 4295;
		int reverse = 0;
		while (foo > 0) {
			int digit = foo % 10;
			reverse = (reverse*10) + digit;
			foo = foo / 10;
		}
		System.out.println("REVERSE=" + reverse);
	}
	
	@Test
	@Ignore
	public void palindrome() {
		String word = "paxap";
	}
	
	
	@Test
	@Ignore
	public void compareString() {
		String word1 = "captain";
		String word2 = "Captain";
		System.out.println("COMPARE STRINGS");
		System.out.println(word1.compareTo(word2));
		System.out.println(word1.compareToIgnoreCase(word2));
		System.out.println(word2.compareTo(word1));
		System.out.println("COMPARE STRINGS END");
		
		List<String> myList = new ArrayList<String>();
		myList.add("Captain");
		myList.add("captain");
		myList.add("Dakota");
		Collections.sort(myList);
		for (String next : myList) {
			System.out.println(next);
		}
		
	}
	
	
	@Test
	@Ignore
	public void useHashMapAsLookup() {
		String name1 = "Fred";
		String name2 = "Barney";
		String name3 = "Betty";
		String name4 = "Wilma";
		HashMap<String,String> names = new HashMap<>();
		
		names.put(name1, name1);
		System.out.println(name1 + " in Map=" + names.containsKey(name1));
		System.out.println(name2 + " in Map=" + names.containsKey(name2));
		
		names.put(name2, name2);
		names.put(name3, name3);
		names.put(name4, name4);
		for (String key : names.keySet()) {
			System.out.println("Next Key=" + key);
		}
		
	}
	
	@Test
	@Ignore
	public void testFinallyJunk() {
		System.out.println("testFinallJunk=" + runFoo());
	}
	
	@Test
	@Ignore
	public void testSortMap() {
		TreeMap<String,String> map = new TreeMap<String,String>();
		map.put("fred", "fred");
		map.put("barney", "barney");
		map.put("wilma", "wilma");
		map.put("betty", "betty");
		for (String key : map.keySet()) {
			System.out.println("Next Key=" + key);
		}
		
	}
	
	
	private int runFoo() {
		try {
			return 42;
		} catch (Exception e) {
			return 43;
		} finally {
			return 55;
		}
	}
	
	
	@Test
	@Ignore
	public void reverseList() {
		
		Node node = new Node("A");
		Node list = node;
		
		Node current = node;
		node = new Node("B");
		current.setNext(node);
		current = node;
		
		node = new Node("C");
		current.setNext(node);
		
		for (node=list; node != null; node=node.getNext()) {
			System.out.println(node.getValue());
		}
		
		list = reverseList(list);
		System.out.println("REVERSE LIST:");
		for (node=list; node != null; node=node.getNext()) {
			System.out.println(node.getValue());
		}
		
	}
	

	private Node reverseList(Node list) {
		Node previous = null;
		Node next = null;
		Node current = list;
		while (current != null) {
			next = current.getNext();
			current.setNext(previous);
			previous = current;
			current = next;
		}
		return previous;
	}
	
	
	@Test
	@Ignore
	public void testDummy() {
		try {
			logger.info("Dummy Test");
		} catch (Exception e) {
			logger.error("ERROR", e);
		}
	}
	

	
	@Test
	@Ignore
	public void testInlineVsConstructor() {
		try {
			logger.info("testInlineVsConstructor");
			Foo foo = new Foo();
			System.out.println("Foo.x=" + foo.getX());
		} catch (Exception e) {
			logger.error("ERROR", e);
		}
	}
	
	
	@Test
	@Ignore
	public void testForEach() {
		try {
			logger.info("ForEach Test");
			List<Integer> myList = new ArrayList<>();
			myList.add(1);
			myList.add(5);
			myList.add(8);
			myList.add(3);
			myList.forEach(new MyConsumer());
		} catch (Exception e) {
			logger.error("ERROR", e);
		}
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
	
	
	@Test
	@Ignore
	public void testStreams() {
		try {
			logger.info("Test Streams BEGIN");
			
			List<Integer> myList = new ArrayList<>();
			for (int i = 0; i < 20; i++) {
				myList.add(i);
			}
			
			List<String> myStrings = new ArrayList<>();
			myStrings.add("5");
			myStrings.add("8");
			myStrings.add("4");
			Stream<String> myStringStream = myStrings.stream();
			
			// Sequential Stream
			Stream<Integer> myStream1 = myList.stream();
			Stream<Integer> myStream2 = myList.stream();
			Stream<Integer> myStream3 = myList.stream();
			
			// Filter
			logger.info("Greater than 10");
			Stream<Integer> bigNumbers = myStream1.filter(i -> {return i > 10;});
			bigNumbers.forEach(i -> {System.out.println("Next Big Number=" + i);});
			
			logger.info("Greater than 15");
			Stream<Integer> numbers15 = myStream2.filter(new GreaterThan15());
			numbers15.forEach(i -> {System.out.println("Next > 15 =" + i);});
			
			// Map Integer to String
			logger.info("Integer to String");
			Stream<String> stringStream = myStream3.map(i -> String.valueOf(i));
			stringStream.forEach(new StringPrintConsumer());
			
			// Map/Reduce
			Integer mySum = myStringStream
					.map(s -> Integer.valueOf(s))
					.reduce(0, new MyAdder());
					
			
			logger.info("Sum of Converted Strings=" + mySum);
			
		} catch (Exception e) {
			logger.error("ERROR", e);
		}
	}
	
	
	
	
	public static class MyIntToStringMapper implements Function<Integer,String> {
		@Override
		public String apply(Integer arg0) {
			return String.valueOf(arg0);
		}
	}
	
	// Reduce operation
	public static class MyAdder implements BinaryOperator<Integer> {
		@Override
		public Integer apply(Integer arg0, Integer arg1) {
			return arg0 + arg1;
		}
	}
	
	public static class GreaterThan15 implements Predicate<Integer> {
		@Override
		public boolean test(Integer arg0) {
			return arg0 > 15;
		}
	}
	
	public static class StringPrintConsumer implements Consumer<String> {
		@Override
		public void accept(String arg) {
			logger.info("Next String=" + arg);
		}
	}
	
	public static class MyFunction implements MyFunctionInterface {
		@Override
		public void doSomeWork() {
			logger.info("DOING SOME WORK HERE!");
		}
	}
	
	
	
	@Test
	@Ignore
	public void testFizzBuzz() {
		for (int i = 1; i <= 100; i++) {
			if ((i%3==0) && (i%5==0)) {
				System.out.println("FizzBuzz: " + i);
			} else if (i%3==0) {
				System.out.println("Fizz: " + i);
			} else if (i%5 ==0) {
				System.out.println("Buzz: " + i);
			} else {
				System.out.println(i);
			}
		}
	}
	
	private static class MyConsumer implements Consumer<Integer> {

		@Override
		public void accept(Integer arg0) {
			logger.info("MyConsumer: " + arg0);
		}
		
	}
	
	
}

package com.rueggerllc.tests;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.rueggerllc.beans.Foo;
import com.rueggerllc.beans.MyButton;

public class RegexTests {

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
	// @Ignore
	public void testFoo() {
		Foo foo = new Foo();
		logger.info("Foo.x=" + foo.getX());
	}
	
	@Test
	// @Ignore
	public void testRegex() {
		logger.info("Test REGEX");
		String data = 
		  "{\"ID\": 10649, \"ORDER_ID\": 85, \"PRODUCT_ID\": \"JT8382X38\", \"CUSTOMER_ID\": 57," +
		  "\"QUANTITY\": 2, \"ORDER_DATE\": \"2018-02-17 11:09:56.217\", \"REGION\": \"Far East\", \"NOTES\": \"Repeat Purchase\"," + 
		  "\"AMOUNT\": \"7997.48\", \"STATUS\": 105}\"";
		logger.info("Data=\n" + data);
		
		
		
	}

	

	
	@Test
	@Ignore
	//  Nifi: (?s:^.*$)
	// Modes:
	// (?i) = case insensitive mode
	// (?s) = single line mode: . matches all characters, includine line breaks
	// (?m) = multi line mode
	public void testRegularExpressions() {
		try {
			String content = "NEW HEAD CONTENT";
			String newResponse = null;
			String response =
					"<html>" +
					"<head>here is Head Content</head>" +
					"<body>Body Content</body>" +
					"<html>";
			if (response.matches("(?s)(?i).*</(\\s)*head(\\s)*>.*") == true) {
				  newResponse = response.replaceFirst("(?s)(?i)</(\\s)*head(\\s)*>", "\n" + content  + "\n</head>");
			}
			logger.info("newResponse=\n" + newResponse);
			
		} catch (Exception e) {
			logger.error("Error", e);
		}
	}
	
	

	
	
}

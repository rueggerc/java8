package com.rueggerllc.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class FutureCompleteTests {

	private static Logger logger = Logger.getLogger(FutureCompleteTests.class);
	
	
	
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
	@Ignore
	public void testCompletableFuture1() {
		logger.info("Test CompletableFuture BEGIN");
		try {
			Future<String> result = callSimple();
			String foo = result.get();
			logger.info("RESULT VALUE=" + foo);
		} catch (InterruptedException e) {
			logger.error("ERROR", e);
		} catch (ExecutionException e) {
			logger.error("ERROR", e);
		}
		logger.info("Test CompletableFuture END");
	}
	
	@Test
	@Ignore
	public void testCompletableFuture2() {
		logger.info("testCompletableFuture2 BEGIN");
		try {
			Future<String> result = CompletableFuture.supplyAsync(new CallTransactionService("42"));
			String resultValue = result.get();
			logger.info("RESULT VALUE=" + resultValue);
			
		} catch (InterruptedException e) {
			logger.error("ERROR", e);
		} catch (ExecutionException e) {
			logger.error("ERROR", e);
		}
		logger.info("testCompletableFuture2 END");
	}
	
	@Test
	@Ignore
	public void testGetAllTransactions1() {
		logger.info("testGetAllTransactions BEGIN");
		List<CompletableFuture<List<String>>> futureResultList = new ArrayList<>();
		futureResultList.add(getListOfTransactions("42"));
		futureResultList.add(getListOfTransactions("43"));
		futureResultList.add(getListOfTransactions("44"));
		
		// Wait for them all to complete
		CompletableFuture[] futureResultArray = futureResultList.toArray(new CompletableFuture[futureResultList.size()]);
		CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(futureResultArray);
		
		logger.info("=========== GOT PAST ALL OF");
		
		CompletableFuture<List<List<String>>> finalResults = combinedFuture
          .thenApply(dummy ->
                futureResultList.stream()
                	.map(future -> future.join())
                	.collect(Collectors.toList()));
		
		// finalResults.thenAccept(result -> System.out.println(result));
		finalResults.thenAccept(new GetFinalList());
		
		logger.info("testGetAllTransactions END");
	}
	
	@Test
	public void testGetAllTransactions2() {
		logger.info("testGetAllTransactions BEGIN");
		List<CompletableFuture<List<String>>> futureResultList = new ArrayList<>();
		futureResultList.add(getListOfTransactions("42"));
		futureResultList.add(getListOfTransactions("43"));
		futureResultList.add(getListOfTransactions("44"));
		
		// Wait for them all to complete
		CompletableFuture[] futureResultArray = futureResultList.toArray(new CompletableFuture[futureResultList.size()]);
		CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(futureResultArray);
		
		logger.info("=========== GOT PAST ALL OF");
		
		CompletableFuture<List<List<String>>> finalResults = combinedFuture
          .thenApply(dummy ->
                futureResultList.stream()
                	.map(future -> future.join())
                	.collect(Collectors.toList()));
		
		// finalResults.thenAccept(result -> System.out.println(result));
		finalResults.thenAccept(new GetFinalList());
		
		logger.info("testGetAllTransactions END");
	}
	
	public static class GetFinalList implements Consumer<List<List<String>>> {

		private List<String> allTransactions = new ArrayList<>();
		@Override
		public void accept(List<List<String>> mainList) {
			for (List<String> nextList : mainList) {
				allTransactions.addAll(nextList);
			}
			for (String nextTransaction : allTransactions) {
				System.out.println("Next Transaction=" + nextTransaction);
			}
			
		}	
	}
	
	public static CompletableFuture<List<String>> getListOfTransactions(String cardNumber) {
        return CompletableFuture.supplyAsync(new CallTransactionService(cardNumber));
    }
	
	private static class CallTransactionService implements Supplier {
		private String cardNumber; 
		public CallTransactionService(String cardNumber) {
			this.cardNumber = cardNumber;
		}
		
		@Override
		public Object get() {
			System.out.println("Get Transactions For Card=" + cardNumber);
			List<String> transactions = new ArrayList<>();
			if (cardNumber.equals("42")) {
				try {
					System.out.println("BEFORE SLEEP");
					System.out.println("AFTER SLEEP");
					transactions.add("42-1");
					transactions.add("42-2");
					transactions.add("42-3");
				} catch (Exception e) {
					System.out.println("ERROR: " +e);
				}

			} else if (cardNumber.equals("43")) {
				transactions.add("43-1");
				transactions.add("43-2");
				transactions.add("43-3");
			} else if (cardNumber.equals("44")) {
				transactions.add("44-1");
				transactions.add("44-2");
				transactions.add("44-3");
			}
			return transactions;
		}
		
	}
	
	private Future<String> callSimple() throws InterruptedException {
	    CompletableFuture<String> completableFuture  = new CompletableFuture<>();
	    Executors.newCachedThreadPool().submit(() -> {
	        Thread.sleep(500);
	        System.out.println("INSIDE SUBMIT FUNCTION");
	        completableFuture.complete("Hello");
	        return null;
	    });
	    return completableFuture;
	}
	
	private Future<String> callREST1() throws InterruptedException {
	    CompletableFuture<String> completableFuture  = new CompletableFuture<>();
	    Executors.newCachedThreadPool().submit(new callHelloService());
	    return completableFuture;
	}
	
	private static class callHelloService implements Runnable {
		private CompletableFuture<String> completableFuture;
		@Override
		public void run() {
			System.out.println("Calling Hello Service");
		}
		
	}
	
	
}

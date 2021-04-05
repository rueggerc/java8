package com.rueggerllc.tests;

import org.apache.log4j.Logger;
import org.junit.*;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class CompleteableFutureTests {

  private static Logger logger = Logger.getLogger(TestCommonElementsInSortedArrays.class);

  @BeforeClass
  public static void setupClass() throws Exception {}

  @AfterClass
  public static void tearDownClass() throws Exception {}

  @Before
  public void setupTest() throws Exception {}

  @After
  public void tearDownTest() throws Exception {}

  @Test
  public void testSayHello() {
    logger.info("sayHello Test");
  }

  @Test
  public void testCF1() {
    try {
      CompletableFuture<String> completableFuture = new CompletableFuture<String>();
      completableFuture.complete("Here is Future Result");
      String result = completableFuture.get();
      logger.info("Result=" + result);
    } catch (Exception e) {
      logger.error("Error" + e);
    }
  }

  @Test
  public void testCF2() {
    try {
      logger.info("CF2 BEGIN");
      List<String> accountNames = Arrays.asList("Account1", "Account2", "Account3", "Account4");
      Map<String, List<String>> allTransactions = new HashMap<>();
      List<String> errors = new ArrayList<>();
      List<CompletableFuture<Void>> futuresList =
          accountNames.stream()
              .map(
                  name -> {
                    return CompletableFuture.supplyAsync(
                            () -> {
                              try {
                                TimeUnit.SECONDS.sleep(1);
                              } catch (InterruptedException e) {
                              }
                              return getTransactions(name);
                            })
                        .thenAccept(
                            transactionsForAccount -> {
                              System.out.println("Accept: Got Transactions for Account=" + name);
                              allTransactions.put(name, transactionsForAccount);
                            })
                        .handle(
                            (result, ex) -> {
                              if (ex != null) {
                                System.out.println("Error in future");
                                errors.add(ex.getMessage());
                                return result;
                              }
                              return result;
                            });
                  })
              .collect(Collectors.toList());
      System.out.println("LIST OF FUTURES CREATED");
      CompletableFuture.allOf(futuresList.toArray(new CompletableFuture[futuresList.size()]))
          .join();
      System.out.println("TRANSACTIONS READ");
      allTransactions
          .entrySet()
          .forEach(
              entry -> {
                System.out.println(
                    "key=" + entry.getKey() + " TransactionList=" + entry.getValue());
              });

    } catch (Exception e) {
      logger.error("Error" + e);
    }
  }

  private List<String> getTransactions(String accountNumber) {
    logger.info("Get Transactions for Account=" + accountNumber);
    List<String> transactions = new ArrayList<>();
    if (accountNumber.equals("Account1")) {
      transactions.add("Account1-TXN1");
    } else if (accountNumber.equals("Account2")) {
      transactions.add("Account1-TXN1");
      transactions.add("Account2-TXN2");
    } else if (accountNumber.equals("Account3")) {
      transactions.add("Account1-TXN1");
      transactions.add("Account2-TXN2");
      transactions.add("Account3-TXN3");
    } else if (accountNumber.equals("Account4")) {
      transactions.add("Account4-TXN1");
      transactions.add("Account4-TXN2");
      transactions.add("Account4-TXN3");
      transactions.add("Account4-TXN4");
    }
    return transactions;
  }
}

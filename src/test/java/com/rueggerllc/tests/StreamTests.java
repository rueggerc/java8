package com.rueggerllc.tests;

import com.rueggerllc.functions.MyFunctionInterface;
import org.apache.log4j.Logger;
import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTests {

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
  public void testFlatMap1() {
    // https://howtodoinjava.com/java8/stream-flatmap-example/
    // int[][] numberLists = new int[][] { {1,2,3}, {4,5,6}};

    List<Integer> list1 = Arrays.asList(1, 2, 3);
    List<Integer> list2 = Arrays.asList(4, 5, 6);
    List<Integer> list3 = Arrays.asList(7, 8, 9);

    List<List<Integer>> listOfLists = Arrays.asList(list1, list2, list3);

    List<Integer> listOfAllIntegers =
        listOfLists.stream().flatMap(nextList -> nextList.stream()).collect(Collectors.toList());

    System.out.println(listOfAllIntegers);
  }

  @Test
  public void testFlatMap2() {

    List<Integer> list1 = Arrays.asList(1, 2, 3);
    List<Integer> list2 = Arrays.asList(4, 5, 6);
    List<Integer> list3 = Arrays.asList(7, 8, 9);

    List<List<Integer>> listOfLists = Arrays.asList(list1, list2, list3);

    Stream streamOfAllIntegers = listOfLists.stream().flatMap(nextList -> nextList.stream());
    streamOfAllIntegers.forEach(
        nextInteger -> {
          System.out.println(nextInteger);
        });
  }

  @Test
  public void testWordCount() {

    List<String> lines = new ArrayList<>();
    lines.add("cow dog cat bird");
    lines.add("alligator fox");
    lines.add("dog cat");
    Stream<String> linesStream = lines.stream();
    // Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8);
    Stream<String> words = linesStream.flatMap(line -> Stream.of(line.split(" +")));
    words.forEach(
        word -> {
          System.out.println(word);
        });
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
  // @Ignore
  public void testStreams1() {
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
      Stream<Integer> bigNumbers =
          myStream1.filter(
              i -> {
                return i > 10;
              });
      bigNumbers.forEach(
          i -> {
            System.out.println("Next Big Number=" + i);
          });

      logger.info("Greater than 15");
      Stream<Integer> numbers15 = myStream2.filter(new GreaterThan15());
      numbers15.forEach(
          i -> {
            System.out.println("Next > 15 =" + i);
          });

      // Map Integer to String
      logger.info("Integer to String");
      Stream<String> stringStream = myStream3.map(i -> String.valueOf(i));
      stringStream.forEach(new StringPrintConsumer());

      // Map/Reduce
      Integer mySum = myStringStream.map(s -> Integer.valueOf(s)).reduce(0, new MyAdder());

      logger.info("Sum of Converted Strings=" + mySum);

    } catch (Exception e) {
      logger.error("ERROR", e);
    }
  }

  public static class MyIntToStringMapper implements Function<Integer, String> {
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
      System.out.println("Inside Greater Than 15");
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

  private static class MyConsumer implements Consumer<Integer> {

    @Override
    public void accept(Integer arg0) {
      logger.info("MyConsumer: " + arg0);
    }
  }
}

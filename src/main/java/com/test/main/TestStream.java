package com.test.main;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
//import static java.util.stream.Collectors.toList;

public class TestStream {
    public static void filter(List<String> languages, Predicate<String> condition) {
        languages.stream().filter(x -> condition.test(x)).forEach(x -> System.out.println(x + " "));

        languages.stream().filter(condition::test).forEach(System.out::println);
        languages.stream().filter(condition).forEach(x -> System.out.println(x + " "));

        languages.stream().filter(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return condition.test(s);
            }
        }).forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s + " ");
            }
        });
    }

    public static void print(String text) {
        // Java 8
        Optional.ofNullable(text).ifPresent(System.out::println);
        // Pre-Java 8
        if (text != null) {
            System.out.println(text);
        }
    }

    public static int getLength(String text) {
        // Java 8
        return Optional.ofNullable(text).map(String::length).orElse(-1);
        // Pre-Java 8
        //return if (text != null) ? text.length() : -1;
    }

    public static void main(String[] args) {
        //创建一个空的stream
        Stream<Integer> stream = Stream.empty();        //比IntStream boxing 和 unboxing 耗时
        //stream.collect(Collectors.toCollection(ArrayList::new));      //参数类型Supplier<T>?
        List<Integer> list = stream.collect(Collectors.toList());

        //一对多映射
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        Stream<Integer> outputStream = inputStream.flatMap((childList) -> childList.stream());
        //Stream<Integer> outputStream = inputStream.flatMap(Collection::stream);

        //静态工厂
        IntStream.range(1, 3).forEach(System.out::println);
        IntStream.rangeClosed(1, 3).forEach(System.out::println);

        //合并所有值
        String[] strArray = {"H","e","l","l","o"};
        Stream<String> strStream = Arrays.stream(strArray);  //stream = Stream.of(strArray);
        System.out.println(strStream.reduce((str,temp) -> str + temp).get());

        //创建无限流，通过limit提取指定大小
        Stream.generate(()->"number"+new Random().nextInt()).limit(10).forEach(System.out::println);
        //返回util.Array.ArrayList 非util.ArrayList
        List<Double> cost = Arrays.asList(10.0, 20.0, 30.0);
        //将一个对象变换为另外一个
        cost.stream().map(x -> x + x * 0.05).forEach(x -> System.out.println(x));

        //合并所有值
        double allCost = cost.stream().reduce((sum, x) -> {
            System.out.println(sum + x);
            return sum + x;
        }).get();

        //过滤
        List<Double> filteredCost = cost.stream().filter(x -> x > 25.0).collect(Collectors.toList());
        filteredCost.forEach(x -> System.out.println(x));

        //Predicate过滤
        List<String> languages = Arrays.asList("Java", "Python", "scala", "Shell", "R");
        System.out.println("Language starts with J: ");
        filter(languages, x -> x.startsWith("J"));

        filter(languages, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.startsWith("J");
            }
        });

        System.out.println("Language ends with a: ");
        filter(languages, x -> x.endsWith("a"));
        System.out.println("All languages: ");
        filter(languages, x -> true);
        System.out.println("No languages: ");
        filter(languages, x -> false);
        System.out.println("Language length bigger three: ");
        filter(languages, x -> x.length() > 4);

        //Optional用例,提供的是编译时检查,为了尽可能避免NullPointerException
        print("abcd");
        print(null);
        getLength("abcd");
        getLength(null);

        //创建Supplier 实例,通过generate方法生成Stream对象
        Random random = new Random();
        /*Supplier<Integer> integerSupplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                return random.nextInt();
            }
        };*/

        Supplier<Integer> integerSupplier = random::nextInt;
        Stream.generate(integerSupplier).limit(10).forEach(System.out::println);
        //无限流,和generate一样需要limit
        Stream.iterate(1,n -> n * 2).limit(5).forEach(System.out::println);

        List<Double> list2 = new ArrayList<Double>();
        for(int i=0;i<10000000;i++){
            double d = Math.random() * 1000;
            list2.add(d);
        }
        list2.stream().parallel().sorted().collect(Collectors.toList());
    }
}

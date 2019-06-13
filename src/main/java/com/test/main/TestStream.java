package com.test.main;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStream {
    public static void main(String[] args) {
        //创建一个空的stream
        Stream<Integer> stream  = Stream.empty();

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
        //同上
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
    }

    public static void filter(List<String> languages, Predicate<String> condition) {
        languages.stream().filter(x -> condition.test(x)).forEach(x -> System.out.println(x + " "));
        //同上
        languages.stream().filter(condition::test).forEach(System.out::println);
        languages.stream().filter(condition).forEach(x -> System.out.println(x + " "));
        //同上
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
}

package com.test.main;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStream {
    public static void main(String[] args) {
        //创建一个空的stream
        Stream<Integer> stream  = Stream.empty();

        //创建无限流，通过limit提取指定大小
        Stream.generate(()->"number"+new Random().nextInt()).limit(100).forEach(System.out::println);

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
        System.out.println("\nLanguage ends with a: ");
        filter(languages, x -> x.endsWith("a"));
        System.out.println("\nAll languages: ");
        filter(languages, x -> true);
        System.out.println("\nNo languages: ");
        filter(languages, x -> false);
        System.out.println("\nLanguage length bigger three: ");
        filter(languages, x -> x.length() > 4);
    }

    public static void filter(List<String> languages, Predicate<String> condition) {
        languages.stream().filter(x -> condition.test(x)).forEach(x -> System.out.println(x + " "));
    }
}

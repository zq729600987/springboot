package main;

import pojo.User;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TestLambda {
    public static void main(String[] args) {
        List<User> userList = new ArrayList<User>() {
            {
                add(new User(4));
                add(new User(2));
                add(new User(1));
                add(new User(3));
            }
        };

        /*Collections.sort(userList, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return Integer.compare(o1.getId(), o2.getId());
            }
        });
        Collections.sort(userList, (o1, o2) -> Integer.compare(o1.getId(), o2.getId()));*/
        Collections.sort(userList, Comparator.comparingInt(User::getId));

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        }).start();
        new Thread(() -> System.out.println("hello world")).start();

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

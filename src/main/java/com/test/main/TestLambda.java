package com.test.main;

import pojo.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestLambda{
    public static void main(String[] args) {
        List<User> userList = new ArrayList<User>() {
            {
                add(new User(4));
                add(new User(2));
                add(new User(1));
                add(new User(3));
            }
        };

        Collections.sort(userList, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return Integer.compare(o1.getId(), o2.getId());
            }
        });
        Collections.sort(userList, (o1, o2) -> Integer.compare(o1.getId(), o2.getId()));
        Collections.sort(userList, Comparator.comparingInt(User::getId));

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        }).start();
        new Thread(() -> System.out.println("hello world")).start();
        new Thread(System.out::println).start();
    }
}

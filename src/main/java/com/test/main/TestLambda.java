package com.test.main;

import com.test.pojo.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestLambda{
    public static void main(String[] args) {
        List<User> userList = new ArrayList<User>() {
            {
                add(new User(4,"王五"));
                add(new User(2,"李四"));
                add(new User(1,"张三"));
                add(new User(2,"李四"));
            }
        };

        /*Collections.sort(userList, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return Integer.compare(o1.getId(), o2.getId());
            }
        });
        Collections.sort(userList, (o1, o2) -> Integer.compare(o1.getId(), o2.getId()));

        Collections.sort(userList, Comparator.comparingInt(new ToIntFunction<User>() {
            @Override
            public int applyAsInt(User user) {
                return user.getId();
            }
        }));
        Collections.sort(userList, Comparator.comparingInt(User::getId));*/

        //多条件排序 关注thenComparing生成的Comparator对象
        Collections.sort(userList, new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        System.out.println("id排序 o1:" + o1.getId() + " o2:" + o2.getId());
                        return Integer.compare(o1.getId(), o2.getId());
                    }
                }.thenComparing(new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        System.out.println("username排序 o1:" + o1.getUsername() + " o2:" + o2.getUsername());
                        return o1.getUsername().compareTo(o2.getUsername());
                    }
                }.thenComparing(new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        System.out.println("username倒序 o1:" + o1.getUsername() + " o2:" + o2.getUsername());
                        return o2.getUsername().compareTo(o1.getUsername());
                    }
                })));
        //同上
        /*Collections.sort(userList, Comparator.comparingInt(User::getId)
                .thenComparing((o1, o2) -> o2.getUsername().compareTo(o1.getUsername())));
        //同上
        Collections.sort(userList, Comparator.comparingInt(User::getId)
                .thenComparing(Comparator.comparing(User::getUsername).reversed()));*/

       /* new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        }).start();
        new Thread(() -> System.out.println("hello world")).start();
        new Thread(System.out::println).start();*/
    }
}

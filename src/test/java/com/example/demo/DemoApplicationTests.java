//package com.example.demo;
//
//import com.example.demo.entity.User;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Comparator;
//import java.util.List;
//import java.util.function.Predicate;
//import java.util.function.Supplier;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//@SpringBootTest
//class DemoApplicationTests {
//
//    public static List<User> users() {
//        List<User> list = Arrays.asList(
//                new User("李星云", 18, 0, "渝州", new BigDecimal(1000)),
//                new User("李星云", 18, 0, "渝州", new BigDecimal(1000)),
//                new User("李星云", 18, 0, "渝州", new BigDecimal(1000)),
//                new User("陆林轩", 16, 1, "渝州", new BigDecimal(500)),
//                new User("姬如雪", 17, 1, "幻音坊", new BigDecimal(800)),
//                new User("袁天罡", 99, 0, "藏兵谷", new BigDecimal(100000)),
//                new User("张子凡", 19, 0, "天师府", new BigDecimal(900)),
//                new User("陆佑劫", 45, 0, "不良人", new BigDecimal(600)),
//                new User("张天师", 48, 0, "天师府", new BigDecimal(1100)),
//                new User("蚩梦", 18, 1, "万毒窟", new BigDecimal(800))
//        );
//        return list;
//    }
//
//    /**
//     * java6 stream特性
//     * filter  筛选  （多个可以用&符号链接）
//     */
//    @Test
//    void filter() {
//        List<User> users = users();
//        List<User> newlist = users.stream().filter(i -> i.getAge() < 20 & i.getName() != "李星云").collect(Collectors.toList());
//        for (User user : newlist) {
//            System.out.println(user.getName() + " --> " + user.getAge());
//        }
//    }
//
//    /**
//     * java6 stream特性
//     * distinct   去重
//     */
//    @Test
//    void distinct() {
//        List<User> users = users();
//        List<User> newlist = users.stream().distinct().filter(i -> i.getAge() < 18).collect(Collectors.toList());
//        for (User user : newlist) {
//            System.out.println(user.getName() + " --> " + user.getAge());
//        }
//    }
//
//
//    /**
//     * java6 stream特性
//     * sorted   排序
//     */
//    @Test
//    void sorted() {
//        List<User> users = users();
////        List<User> newlist = users.stream().distinct()
////                .sorted(Comparator.comparing(user -> {
////                   return user.getAge();
////                }))
////                .collect(Collectors.toList());
//        List<User> newlist = users.stream().distinct()
//                .filter(user -> user.getAge() < 50)
//                .sorted(Comparator.comparing(User::getAge))
//                .collect(Collectors.toList());
//        for (User user : newlist) {
//            System.out.println(user.getName() + " --> " + user.getAge());
//        }
//    }
//
//    /**
//     * java6 stream特性
//     * limit   返回前n个值
//     */
//    @Test
//    void limit() {
//        List<User> users = users();
//        List<User> newlist = users.stream().distinct()
//                .filter(user -> user.getAge() < 50)
//                .sorted(Comparator.comparing(User::getAge))
//                .limit(2)
//                .collect(Collectors.toList());
//        for (User user : newlist) {
//            System.out.println(user.getName() + " --> " + user.getAge());
//        }
//    }
//
//
//    /**
//     * java6 stream特性
//     * skip   去除前n个值
//     */
//    @Test
//    void skip() {
//        List<User> users = users();
//        List<User> newlist = users.stream().distinct()
//                .filter(user -> user.getAge() < 50)
//                .sorted(Comparator.comparing(User::getAge))
//                .skip(2)
//                .collect(Collectors.toList());
//        for (User user : newlist) {
//            System.out.println(user.getName() + " --> " + user.getAge());
//        }
//    }
//
//
//    /**
//     * java6 stream特性
//     * map() 类型转换(T->R)一对一
//     */
//    @Test
//    void map() {
//        List<User> users = users();
//        List<String> newlist = users.stream().map(User::getName).distinct().collect(Collectors.toList());
//        for (String add : newlist) {
//            System.out.println(add);
//        }
//    }
//
//
//    /**
//     * java6 stream特性
//     * flatmap() 类型转换(T -> Stream<R>)一对多
//     */
//    @Test
//    void flatmap() {
//        List<String> flatmap = new ArrayList<>();
//        flatmap.add("常宣灵,常昊灵");
//        flatmap.add("孟婆,判官红,判官蓝");
//        /*
//            这里原集合中的数据由逗号分割，使用split进行拆分后，得到的是Stream<String[]>，
//            字符串数组组成的流，要使用flatMap的Arrays::stream
//            将Stream<String[]>转为Stream<String>,然后把流相连接
//        */
//        flatmap = flatmap.stream()
//                .map(s -> s.split(","))
//                .flatMap(Arrays::stream)
//                .collect(Collectors.toList());
//        for (String name : flatmap) {
//            System.out.println(name);
//        }
//    }
//
//    @Test
//    void flatmap2() {
//        String[] strings = {"Hello", "World"};
//        Stream<String> stream = Arrays.stream(strings);
//        List<String> collect = stream.map(i -> i.split("")).flatMap(Arrays::stream).collect(Collectors.toList());
//        System.out.println(collect);
//    }
//
//
//
//}

package com.example.demo;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.example.demo.entity.User;
import com.example.demo.function.KiteFunction;
import com.rabbitmq.client.UnblockedCallback;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@SpringBootTest
public class TestJava8 {

    public static class Emp {
        private String name;

        public Emp() {

        }

        public Emp(String name) {
            super();
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    @Test
    void testSupplier() {
        Supplier<String> supplier = String::new;
        System.out.println("******" + supplier.get());//""
        Supplier<Emp> supplierEmp = Emp::new;
        Emp emp = supplierEmp.get();
        emp.setName("dd");
        System.out.println(emp.getName());//dd
    }

    @Test
    public void test1() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello runnable");
            }
        };
        r.run();
        Runnable r1 = () -> System.out.println("hello lambda");
        r1.run();
    }


    @Test
    public void test2() {
        Consumer<String> con = (x) -> System.out.println(x);
        con.accept("有一个参数，无返回值的用法（Consumer函数式接口）");
    }


    @Test
    public void test3() {
        Consumer<Integer> consumer = x -> {
            int a = x + 2;
            System.out.println(a);// 12
            System.out.println(a + "_");// 12_
        };
        consumer.accept(10);
    }


    @Test
    public void test4() {
        String dateString = new KiteFunction<LocalDateTime, String, String>() {
            @Override
            public String run(LocalDateTime localDateTime, String s) {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(s);
                return localDateTime.format(dateTimeFormatter);
            }
        }.run(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss");
        System.out.println(dateString);


        //匿名函数用lambda的写法
        KiteFunction<LocalDateTime, String, String> function = (LocalDateTime dateTime, String partten) -> {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(partten);
            return dateTime.format(dateTimeFormatter);
        };
        String run = function.run(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss");
        System.out.println(run);

    }


    @Test
    public void test5() {
//        Stream<Integer> integerStream = Stream.of(2, 21, 33, 2, 99);
//        Integer integer = integerStream.max(Integer::compare).get();
//        System.out.println(integer);


//        Consumer<String> temp=x->{
//            String s = x + "-";
//            System.out.println(s);
//        };
//        temp.accept("aa");
//        temp.andThen(temp);
//
//
//        Consumer<String> customizer = s -> System.out.println("aa");
//
//        Supplier<Double> supplier = () -> {
//            return Math.random();
//        };
//        supplier.get();
//
//        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
//        //返回一个optional对象
//        Optional<Integer> first = stream.filter(i -> i > 5)
//                .findFirst();
//        first.get();
//        System.out.println(first.orElseGet(()->{return 1;}));

//        Stream<Integer> integerStream = Stream.of(1,2,5,7,8,12,33);
//        Integer sum = integerStream.reduce(0,(x,y)->x+y);
//        System.out.println(sum);



    }


}

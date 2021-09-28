package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.Consumer;
import java.util.function.Supplier;


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
        System.out.println("******"+supplier.get());//""
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
    public void test2(){
        Consumer<String> con = (x) -> System.out.println(x);
        con.accept("有一个参数，无返回值的用法（Consumer函数式接口）");
    }


    @Test
    public void test3(){
        Consumer<Integer> consumer = x -> {
            int a = x + 2;
            System.out.println(a);// 12
            System.out.println(a + "_");// 12_
        };
        consumer.accept(10);
    }


}

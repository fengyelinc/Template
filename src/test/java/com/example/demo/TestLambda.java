//package com.example.demo;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.*;
//
//@SpringBootTest
//public class TestLambda {
//
//    @Test
//    public void test6() {
//        /*************** 方法的引用 ****************/
//        // 类：：静态方法名
//        Comparator<Integer> bb = Integer::compare;
//        System.out.println(bb.compare(3, 2));
//        Comparator<Integer> cc = (x, y) -> Integer.compare(x, y);
//        System.out.println(cc.compare(3, 2));
//
//        Comparator<Integer> dd = (x, y) -> x.compareTo(y);
//        System.out.println(dd.compare(3, 2));
//        Comparator<Integer> ee = Integer::compareTo;
//        System.out.println(ee.compare(3, 2));
//        // 类：：实例方法名
//        BiPredicate<String, String> bp = (x, y) -> x.equals(y);
//        System.out.println(bp.test("a", "b"));
//        BiPredicate<String, String> bp1 = String::equals;
//        System.out.println(bp1.test("a", "b"));
//
//        // 对象：：实例方法名
//        Consumer<String> con1 = x -> System.out.println(x);
//        con1.accept("abc");
//        Consumer<String> con = System.out::println;
//        con.accept("abc");
//
//        Emp emp = new Emp("上海", "xiaoMIng", 18);
//        Supplier<String> supper1 = () -> emp.getAddress();
//        System.out.println(supper1.get());
//        Supplier<String> supper = emp::getAddress;
//        System.out.println(supper.get());
//
//        /*************** 构造器的引用 ****************/
//        // 无参构造函数，创建实例
//        Supplier<Emp> supper2 = () -> new Emp();
//        Supplier<Emp> supper3 = Emp::new;
//        Emp emp1 = supper3.get();
//        emp1.setAddress("上海");
//        // 一个参数
//        Function<String, Emp> fun = address -> new Emp(address);
//        Function<String, Emp> fun1 = Emp::new;
//        System.out.println(fun1.apply("beijing"));
//        // 两个参数
//        BiFunction<String, Integer, Emp> bFun = (name, age) -> new Emp(name, age);
//        BiFunction<String, Integer, Emp> bFun1 = Emp::new;
//        System.out.println(bFun1.apply("xiaohong", 18));
//
//    }
//
//    @Test
//    void test1(){
//        Function<Integer, Integer> name = e -> e * 2;
//        Function<Integer, Integer> square = e -> e * e;
//        //最后调用的.apply()用来传递参数，在这边应该是固定的写法
//        //先执行外面的3*2=6   再执行参数里的6*6=36
//        int value = name.andThen(square).apply(3);
//        System.out.println("andThen value=" + value);
//
//        //先执行参数里的3*3=9  再执行外面的9*2=18
//        int value2 = name.compose(square).apply(3);
//        System.out.println("compose value2=" + value2);
//
//        //返回一个执行了apply()方法之后只会返回输入参数的函数对象
//        Object identity = Function.identity().apply("huohuo");
//        System.out.println(identity);
//
//    }
//
//    @Test
//    void test2(){
//        Predicate<Integer> biggerThan6 = x -> x > 6;
//        Predicate<Integer> lessThan3 = x -> x < 3;
//        Predicate<Integer> lessThan9 = x -> x < 9;
//        //7比6大为true，为false
//        System.out.println("negate value=" + biggerThan6.negate().test(7));
//        //7比6大为true
//        System.out.println("test() value=" + biggerThan6.test(7));
//        //8比6大且比9小
//        System.out.println("and value=" + biggerThan6.and(lessThan9).test(8));
//        //1比3小，满足一种条件
//        System.out.println("or value=" + biggerThan6.or(lessThan3).test(1));
//        //静态方法，判定是否相等
//        System.out.println("isEqual false value=" + Predicate.isEqual("test").test("num"));
//        System.out.println("isEqual true value=" + Predicate.isEqual("num").test("num"));
//        Emp emp1 = new Emp();
//        Emp emp2 = new Emp();
//        System.out.println("isEqual entity value=" + Predicate.isEqual(emp1).test(emp2));
//    }
//
//    @Test
//    void test3(){
//        Map<String, String> map = new HashMap<>();
//        map.put("a", "1");
//        map.put("b", "2");
//        map.put("c", "3");
//        map.put("d", "4");
//        ArrayList<Object> aa = new ArrayList<>();
//        map.forEach((k, v) -> {
//            aa.add(v);
//        });
//        aa.forEach(System.out::println);
//    }
//
//    static class Emp {
//        private String address;
//
//        private String name;
//
//        private Integer age;
//
//        public Emp() {
//
//        }
//
//        public Emp(String address) {
//            this.address = address;
//        }
//
//        public Emp(String name, Integer age) {
//            this.name = name;
//            this.age = age;
//        }
//
//        public Emp(String address, String name, Integer age) {
//            super();
//            this.address = address;
//            this.name = name;
//            this.age = age;
//        }
//
//        public String getAddress() {
//            return address;
//        }
//
//        public void setAddress(String address) {
//            this.address = address;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public Integer getAge() {
//            return age;
//        }
//
//        public void setAge(Integer age) {
//            this.age = age;
//        }
//
//        @Override
//        public String toString() {
//            return "Emp [address=" + address + ", name=" + name + ", age=" + age + "]";
//        }
//
//    }
//}

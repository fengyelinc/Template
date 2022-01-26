package com.example.demo;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class TreeTest {

    @Test
    public void test1(){
        BigDecimal price=new BigDecimal(4855.9);
        BigDecimal count = new BigDecimal(7);
        BigDecimal divide = price.multiply(count).divide(BigDecimal.TEN, 2, BigDecimal.ROUND_HALF_UP);
        System.out.println(divide);
    }
}

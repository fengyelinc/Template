package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
public class RegexTest {
    @Test
    void test1() {
        String str = "The cat scattered his food all over the room.";
        Pattern p = Pattern.compile("^cat$");
        Matcher matcher = p.matcher(str);

        while(matcher.find()){
            System.out.println(matcher.group());
        }
    }

}

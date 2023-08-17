package com.example.demo;


import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


public class TestZH {


    public static void main(String[] args) throws IOException {
        System.setProperty("webdriver.edge.driver", "D:\\EVN\\edgeDriver\\edgedriver_win64\\msedgedriver.exe");

        EdgeOptions options = new EdgeOptions();
        WebDriver driver = new EdgeDriver(options);
        String cookie = "[{\"name\":\"XSRF-TOKEN\",\"value\":\"qnORqLa8fT71Z4Ci-3KbZCR6\",\"domain\":\"weibo.com\",\"hostOnly\":true,\"path\":\"/\",\"secure\":true,\"httpOnly\":false,\"session\":true,\"sameSite\":\"None\"},{\"name\":\"XSRF-TOKEN\",\"value\":\"o8_uN_u3HIQSzrSR3dnTr0Tb\",\"domain\":\".weibo.com\",\"hostOnly\":false,\"path\":\"/\",\"secure\":true,\"httpOnly\":false,\"session\":true,\"sameSite\":\"None\"},{\"name\":\"SUB\",\"value\":\"_2A25J30w1DeRhGeNI7FAY9ivNyz6IHXVqrTr9rDV8PUNbmtANLVTkkW9NSC3StyclyOgSpIgVjmWG4bb-QmZMafFt\",\"domain\":\".weibo.com\",\"hostOnly\":false,\"path\":\"/\",\"secure\":true,\"httpOnly\":true,\"session\":true,\"sameSite\":\"None\"},{\"name\":\"SUBP\",\"value\":\"0033WrSXqPxfM725Ws9jqgMF55529P9D9WFfEW0SpTHPRu0AWJHbcEvk5JpX5KzhUgL.Fo-cS0z4So-pehz2dJLoIEBLxKqLBoBLB.-LxK-LBKBLBKMLxKBLBonLBoqLxK-L1h-LBoBt\",\"domain\":\".weibo.com\",\"hostOnly\":false,\"path\":\"/\",\"secure\":true,\"httpOnly\":false,\"session\":true,\"sameSite\":\"None\"},{\"name\":\"ALF\",\"value\":\"1723625444\",\"domain\":\".weibo.com\",\"hostOnly\":false,\"path\":\"/\",\"secure\":true,\"httpOnly\":false,\"session\":true,\"sameSite\":\"None\"},{\"name\":\"SSOLoginState\",\"value\":\"1692089445\",\"domain\":\".weibo.com\",\"hostOnly\":false,\"path\":\"/\",\"secure\":true,\"httpOnly\":false,\"session\":true,\"sameSite\":\"None\"},{\"name\":\"WBPSESS\",\"value\":\"l1Xqy8jVo-0QyYpPRb46FfI0gI1as2nUZSH_goJldez5Uni3j2OP8qjaj-DaTKK0Zt2Kl2BkO-xca9nsfVV2Dj9ec17mQSIE26GJayRvTbgJAUQnb9GOHE___io1T5v6UVrmB8oMzVVaKANA9CmfMg==\",\"domain\":\".weibo.com\",\"hostOnly\":false,\"path\":\"/\",\"secure\":true,\"httpOnly\":true,\"session\":true,\"sameSite\":\"None\"},{\"name\":\"WBPSESS\",\"value\":\"l1Xqy8jVo-0QyYpPRb46FfI0gI1as2nUZSH_goJldez5Uni3j2OP8qjaj-DaTKK0Zt2Kl2BkO-xca9nsfVV2DjWoNKKQx8gppGuA9bcGjJeh_6rV-jANcR3XNeEJ0LMFRrbfi5fA62TnpTWPGDAy5A==\",\"domain\":\"weibo.com\",\"hostOnly\":true,\"path\":\"/\",\"secure\":true,\"httpOnly\":true,\"session\":false,\"expirationDate\":1692176123.805426,\"sameSite\":\"None\"}]";


        JSONArray jsonArray = JSONUtil.parseArray(cookie);
        //处理 InvalidCookieDomainException
        driver.get("https://weibo.com/newlogin");


        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Cookie c1 = new Cookie(jsonObject.getStr("name"),
                    jsonObject.getStr("value"),
                    jsonObject.getStr("domain"),
                    jsonObject.getStr("path"),
                    jsonObject.getDate("expirationDate", null),
                    jsonObject.getBool("secure"),
                    jsonObject.getBool("httpOnly"));
            driver.manage().addCookie(c1);
        }
        // 刷新页面
        driver.get("https://weibo.com/newlogin");

        String title = driver.getTitle();
        System.out.println(title);

        //进入个人主页
        Actions action = new Actions(driver);
        WebElement goHome = driver.findElement(By.cssSelector(".Ctrls_alink_1L3hP:last-child"));
        action.click(goHome).perform();

        // 粉丝数
        List<WebElement> follows = driver.findElements(By.cssSelector("a.ALink_none_1w6rm.ProfileHeader_alink_tjHJR.ProfileHeader_pointer_2yKGQ>span>span"));
        System.out.println("粉丝 ：" + follows.get(0).getText());

        //浏览量
        List<WebElement> elements = driver.findElements(By.cssSelector(".readnum_num_1Lix5"));
        List<Integer> collect = elements.stream().map(x -> Integer.parseInt(x.getText())).collect(Collectors.toList());
        int readNums = collect.stream().reduce(0, (a, b) -> a + b);
        System.out.println("浏览量：" + readNums);

        //转发
        List<WebElement> forward = driver.findElements(By.cssSelector(".woo-box-flex.woo-box-alignCenter.toolbar_left_2vlsY.toolbar_main_3Mxwo .woo-pop-ctrl .toolbar_num_JXZul"));
        int forwardNums = forward.stream()
                .map(x -> x.getText())
                .filter(x -> !x.contains("转发"))
                .mapToInt(Integer::parseInt)
                .sum();
        System.out.println("转发量：" + forwardNums);

        //评论
        List<WebElement> comment = driver.findElements(By.cssSelector(".Feed_body_3R0rO+footer :has(> [title='评论'])+.toolbar_num_JXZul"));
        int commentNums = comment.stream()
                .map(x -> x.getText())
                .filter(x -> !x.contains("评论"))
                .mapToInt(Integer::parseInt)
                .sum();
        System.out.println("评论数：" + commentNums);

        //点赞
        List<WebElement> like = driver.findElements(By.cssSelector(".Feed_body_3R0rO+footer .woo-like-main.toolbar_btn_Cg9tz>.woo-like-count"));
        int likeNums = like.stream()
                .map(x -> x.getText())
                .filter(x -> !x.contains("赞"))
                .mapToInt(Integer::parseInt)
                .sum();
        System.out.println("点赞数：" + likeNums);



    }


}

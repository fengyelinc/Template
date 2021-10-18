//package com.example.demo.rabbitmq.workQuene;
//
//import com.example.demo.utils.RabbitmqUtil;
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.Connection;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.io.IOException;
///**
// * 任务队列(能者多劳)
// */
//@SpringBootTest
//public class Provider {
//
//    public static void main(String[] args) throws IOException {
//        //获取连接对象
//        Connection connect = RabbitmqUtil.getConnect();
//        //后去通道对象
//        Channel channel = connect.createChannel();
//        //通过通道声明队列
//        channel.queueDeclare("work",false,false,false,null);
//
//        for (int i = 0; i <=20; i++) {
//            //生产消息
//            channel.basicPublish("","work", null,(i+"hello work quene").getBytes());
//        }
//
//        //关闭资源
//        RabbitmqUtil.closeConnectionAndChannel(channel,connect);
//
//    }
//}

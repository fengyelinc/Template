//package com.example.demo.rabbitmq.workQuene;
//
//import com.example.demo.utils.RabbitmqUtil;
//import com.rabbitmq.client.*;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.io.IOException;
//@SpringBootTest
//public class Customer2 {
//    public static void main(String[] args) throws IOException {
//        //获取连接对象
//        Connection connect = RabbitmqUtil.getConnect();
//        //后去通道对象
//        Channel channel = connect.createChannel();
//        //每次只能消费一个消息
//        channel.basicQos(1);
//        //通过通道声明队列
//        channel.queueDeclare("work", false, false, false, null);
//
//        channel.basicConsume("work", false, new DefaultConsumer(channel) {
//                    @Override
//                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                        System.out.println("消费者-2："+new String(body));
//                        //手动确认   参数1：确认队列中哪个具体消息 参数2：是否开启多个消息同时确认
//                        channel.basicAck(envelope.getDeliveryTag(),false);
//                    }
//                }
//        );
//    }
//}
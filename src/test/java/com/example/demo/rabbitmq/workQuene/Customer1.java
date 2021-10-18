//package com.example.demo.rabbitmq.workQuene;
//
//import com.example.demo.utils.RabbitmqUtil;
//import com.rabbitmq.client.*;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.io.IOException;
//
//
//@SpringBootTest
//public class Customer1 {
//    public static void main(String[] args) throws IOException {
//        //获取连接对象
//        Connection connect = RabbitmqUtil.getConnect();
//        //后去通道对象
//        Channel channel = connect.createChannel();
//        //每次只能消费一个消息
//        channel.basicQos(1);
//        //通过通道声明队列
//        channel.queueDeclare("work", false, false, false, null);
//        //参数2：消息自动确认 true 消费者自动向rabbitmq确认消息消费 false 不会自动确认消息
//        channel.basicConsume("work", false, new DefaultConsumer(channel) {
//                    @Override
//                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                        try {
//                            Thread.sleep(2000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        System.out.println("消费者-1："+new String(body));
//                        //手动确认   参数1：确认队列中哪个具体消息 参数2：是否开启多个消息同时确认
//                        channel.basicAck(envelope.getDeliveryTag(),false);
//                    }
//                }
//        );
//    }
//}
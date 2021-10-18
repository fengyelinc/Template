//package com.example.demo.rabbitmq;
//
//import com.rabbitmq.client.*;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.io.IOException;
//import java.util.concurrent.TimeoutException;
//
///**
// * 一对一
// */
//@SpringBootTest
//public class Customer {
//    public static void main(String[] args) throws IOException, TimeoutException {
//        //设置连接 工厂
//        ConnectionFactory connectionFactory = new ConnectionFactory();
//        //设置主机
//        connectionFactory.setHost("127.0.0.1");
//        //设置端口
//        connectionFactory.setPort(5672);
//        //设置虚拟机
//        connectionFactory.setVirtualHost("/ems");
//        connectionFactory.setUsername("cc");
//        connectionFactory.setPassword("123");
//        //获取连接对象
//        Connection connection = connectionFactory.newConnection();
//        //获取连接中的通道对象
//        Channel channel = connection.createChannel();
//        //通道绑定对应的消息队列
//        /**
//         * 参数一：队列名称 （不存在则自动创建）
//         * 参数二;用来定义队列是否持久化  true 持久化队列 | false 不持久化队列
//         * 参数三：是否独占队列 true 独占队列 | false 不独占队列
//         * 参数四：是否在消息完成后自动删除队列  true 自动删除  | false 不自动删除
//         * 参数五：额外附加参数
//         */
//        channel.queueDeclare("hello",true,false,false,null);
//
//        //消费消息
//        /**
//         * 参数一：消费那个队列的消息
//         * 参数二：开启消息的自动确认机制
//         * 参数三：消费消息是的回调接口
//         */
//        channel.basicConsume("hello",true,new DefaultConsumer(channel){
//            /**
//             *
//             * @param consumerTag
//             * @param envelope
//             * @param properties
//             * @param body  消息队列中取出的消息
//             * @throws IOException
//             */
//            @Override
//            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                System.out.println("new String(body) = "+new String(body));
//            }
//        });
//
//
//    }
//
//
//}

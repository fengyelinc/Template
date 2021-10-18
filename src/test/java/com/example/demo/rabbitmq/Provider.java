//package com.example.demo.rabbitmq;
//
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.Connection;
//import com.rabbitmq.client.ConnectionFactory;
//import com.rabbitmq.client.MessageProperties;
//import lombok.SneakyThrows;
//import org.junit.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//public class Provider {
//
//    //生产消息
//    @SneakyThrows
//    @Test
//    public void testSendMessage(){
//        //创建连接mq的生产对象
//        ConnectionFactory connectionFactory = new ConnectionFactory();
//        //设置连接rabbitmq的主机
//        connectionFactory.setHost("127.0.0.1");
//        //设置端口号
//        connectionFactory.setPort(5672);
//        //设置连接那个虚拟主机
//        connectionFactory.setVirtualHost("/ems");
//        //设置访问虚拟主机的用户名密码C
//        connectionFactory.setUsername("cc");
//        connectionFactory.setPassword("123");
//
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
//        //发布消息
//        /**
//         * 参数一;exchange 交换机
//         * 参数二：routinqKey 队列名
//         * 参数三;传递消息的额外设置  (MessageProperties.PERSISTENT_TEXT_PLAIN  消息持久化)
//         * 参数四：消息内容
//         */
//        channel.basicPublish("","hello", MessageProperties.PERSISTENT_TEXT_PLAIN,"hello rabbitmq".getBytes());
//
//        channel.close();
//        connection.close();
//
//    }
//}

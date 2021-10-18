package com.example.demo.rabbitmq.direct;

import com.example.demo.utils.RabbitmqUtil;
import com.rabbitmq.client.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;


@SpringBootTest
public class Customer2 {
    public static void main(String[] args) throws IOException {
        //获取连接对象
        Connection connect = RabbitmqUtil.getConnect();
        //后去通道对象
        Channel channel = connect.createChannel();
       //通道绑定交换机
        channel.exchangeDeclare("logs_direct","direct");
        //临时队列
        String queueName = channel.queueDeclare().getQueue();
        //绑定交换机和队列
        channel.queueBind(queueName,"logs_direct","info");
        channel.queueBind(queueName,"logs_direct","error");
        channel.queueBind(queueName,"logs_direct","warning");
        //消费消息
        channel.basicConsume(queueName,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者 2"+ new String(body));
            }
        });
    }
}
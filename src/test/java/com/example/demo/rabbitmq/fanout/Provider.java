package com.example.demo.rabbitmq.fanout;

import com.example.demo.utils.RabbitmqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * 广播
 */
@SpringBootTest
public class Provider {
    public static void main(String[] args) throws IOException {
        //获取连接对象
        Connection connect = RabbitmqUtil.getConnect();
        //后去通道对象
        Channel channel = connect.createChannel();
        //将通道声明交换机
        /**
         * 参数1：交换机名称
         * 参数2：交换机类型  fanout 广播类型
         */
        channel.exchangeDeclare("logs","fanout");
        //发送消息
        channel.basicPublish("logs","",null,"fanout type message".getBytes());
        //释放资源
        RabbitmqUtil.closeConnectionAndChannel(channel,connect);

    }
}

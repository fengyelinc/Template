package com.example.demo.rabbitmq.direct;

import com.example.demo.utils.RabbitmqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * routing 订阅模型-直连 direct
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
         * 参数2：交换机类型  direct 路由模式
         */
        channel.exchangeDeclare("logs_direct","direct");
        //发送消息
        String routingKey ="info";
        channel.basicPublish("logs_direct",routingKey,null,("这是基于direct发布的基于routingKey：【"+routingKey+"】发送的消息").getBytes());
        //释放资源
        RabbitmqUtil.closeConnectionAndChannel(channel,connect);

    }
}

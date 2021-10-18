package com.example.demo.utils;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitmqUtil {


    private static ConnectionFactory connectionFactory;

    static{
        //重量级资源  类加载执行只执行一次
        //创建连接mq的生产对象
        connectionFactory = new ConnectionFactory();
        //设置连接rabbitmq的主机
        connectionFactory.setHost("127.0.0.1");
        //设置端口号
        connectionFactory.setPort(5672);
        //设置连接那个虚拟主机
        connectionFactory.setVirtualHost("/ems");
        //设置访问虚拟主机的用户名密码
        connectionFactory.setUsername("cc");
        connectionFactory.setPassword("123");
    }

    //定义提供链接对象的方法
    public static Connection getConnect() {
        try {
            return connectionFactory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

    //关闭通道和关闭链接工具方法
    public static void closeConnectionAndChannel(Channel channel, Connection conn) {
        try {
            if(channel!=null)channel.close();
            if(conn!=null)conn.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

}

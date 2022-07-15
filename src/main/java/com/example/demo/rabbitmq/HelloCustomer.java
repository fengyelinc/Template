//package com.example.demo.rabbitmq;
//
//import org.springframework.amqp.rabbit.annotation.Queue;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@Component   //持久化 非独占 不是自动删除队列
//@RabbitListener(queuesToDeclare = @Queue(value = "hello"))
//public class HelloCustomer {
//
//    //回调方法处理队列中的消息
//    @RabbitHandler
//    public void receivel(String message){
//        System.out.println("message ="+message);
//    }
//
//}

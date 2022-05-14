//package com.example.demo.rabbitmq;
//
//import org.springframework.amqp.rabbit.annotation.Queue;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class WorkCustomer {
//
//    //回调方法处理队列中的消息
//    @RabbitListener(queuesToDeclare = @Queue(value = "work"))
//    public void receive1(String message){
//        System.out.println("message1 ="+message);
//    }
//
//    @RabbitListener(queuesToDeclare = @Queue(value = "work"))
//    public void receive2(String message){
//        System.out.println("message2 ="+message);
//    }
//
//}

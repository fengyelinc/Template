//package com.example.demo.rabbitmq;
//
//import org.springframework.amqp.rabbit.annotation.Exchange;
//import org.springframework.amqp.rabbit.annotation.Queue;
//import org.springframework.amqp.rabbit.annotation.QueueBinding;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class FanoutCustomer {
//
//    //回调方法处理队列中的消息
//    @RabbitListener(bindings = {
//            @QueueBinding(value = @Queue,//临时队列
//                    exchange = @Exchange(value = "logs",type = "fanout")  //绑定交换机
//            )
//    })
//    public void receive1(String message){
//        System.out.println("message1 ="+message);
//    }
//
//
//    //回调方法处理队列中的消息
//    @RabbitListener(bindings = {
//            @QueueBinding(value = @Queue,//临时队列
//                    exchange = @Exchange(value = "logs",type = "fanout")  //绑定交换机
//            )
//    })
//    public void receive2(String message){
//        System.out.println("message2 ="+message);
//    }
//}

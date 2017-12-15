package com.dhc.ttshop.activmq;

/**
 * User: DHC
 * Date: 2017/12/8
 * Time: 14:15
 * Version:V1.0
 */
//public class TestTopic {
//    @Test
//    public void testPublisher() throws Exception{
//        //ActiveMQConnectionFactory（tcp  61616生产者消费者端口号要对应）---Connection ---- 连接 --- Session(false,自动应答)--目标对象（Queue，Topic）
//        //创建生产者---创建TextMessage---通过生产者发送消息(send(textMessage))--关闭资源
//        //1 创建连接工厂
//        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://10.31.164.63:61616");
//        //2 通过连接工厂创建连接
//        Connection connection = connectionFactory.createConnection();
//        //3 连接
//        connection.start();
//        //4 通过连接创建会话
//        //只有第一个参数为false时候，第二个参数才有意义，AUTO_ACKNOWLEDGE自动应答，一般选择这个。
//        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//        //5 创建目标对象(消息形式 点对点模式 发布/订阅模式)
//        //队列的name叫firstqueue
//        Topic topic = session.createTopic("firsttopic");
//        //6 创建生产者
//        MessageProducer producer = session.createProducer(topic);
//        //7 创建消息
//        TextMessage message = session.createTextMessage("this is my topic message to you !");
//        //8 发送消息
//        producer.send(message);
//        //9 释放资源
//        producer.close();
//        session.close();
//        connection.close();
//    }
//
//    @Test
//    public void testSubscriber() throws Exception{
//        //线程1
//        //ActiveMQConnectionFactory（tcp  61616生产者消费者端口号要对应）---Connection ---- 连接 --- Session(false,自动应答)--目标对象（Queue，Topic）
//        //创建生产者---创建TextMessage---通过生产者发送消息(send(textMessage))--关闭资源
//        //1 创建连接工厂
//        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://10.31.164.63:61616");
//        //2 通过连接工厂创建连接
//        Connection connection = connectionFactory.createConnection();
//        //3 连接
//        connection.start();
//        //4 通过连接创建会话
//        //只有第一个参数为false时候，第二个参数才有意义，AUTO_ACKNOWLEDGE自动应答，一般选择这个。
//        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//        //5 创建目标对象(消息形式 点对点模式 发布/订阅模式)
//        //队列的name叫firstqueue
//        Topic topic = session.createTopic("firsttopic");
//        //6 创建消费者
//        MessageConsumer consumer = session.createConsumer(topic);
//        //7 接收消息
//        consumer.setMessageListener(new MessageListener() {
//            @Override
//            public void onMessage(Message message) {
//                //a 接收消息的第一步：强制类型转换
//                TextMessage textMessage = (TextMessage) message;
//                try {
//                    //线程2
//                    String str = textMessage.getText();
//                    System.out.println(str);
//                } catch (JMSException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        System.in.read();
//
//        //8 释放资源
//        consumer.close();
//        session.close();
//        connection.close();
//    }
//}

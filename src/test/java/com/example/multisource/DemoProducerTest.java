package com.example.multisource;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


public class DemoProducerTest {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.46.128:61616");
        Connection connection=connectionFactory.createConnection("admin","admin");
        connection.start();
        Session session= connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue= session.createQueue("demo");
        MessageProducer producer=session.createProducer(queue);
        TextMessage message=session.createTextMessage("你好！");
        for (int i = 0; i <10 ; i++)
        {
            producer.send(message);
        }
        connection.close();
        producer.close();
        session.close();
    }
}

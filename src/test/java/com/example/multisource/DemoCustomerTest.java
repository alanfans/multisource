package com.example.multisource;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Enumeration;

public class DemoCustomerTest {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.46.128:61616");
        Connection connection=connectionFactory.createConnection("admin","admin");
        connection.start();
        Session session= connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue= session.createQueue("demo");
        MessageConsumer consumer=session.createConsumer(queue);
        MyMessage message=new MyMessage(false);
        consumer.setMessageListener(message);
        connection.close();
        consumer.close();
        session.close();

    }

    private static class MyMessage implements MessageListener{
        private boolean isOver;

        public boolean isOver() {
            return isOver;
        }

        public MyMessage(boolean isOver) {
            this.isOver = isOver;
        }

        @Override
        public void onMessage(Message message) {
            TextMessage message1=(TextMessage)message;
            try {
                System.out.println(message1.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}

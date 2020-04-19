package com.fuhe.chen.vendingmachine.common.mqtt;

import com.fuhe.chen.vendingmachine.constant.MQTTConst;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Component;

@Component
public class ServerMQTT {
    private final MqttConnectOptions options;
    //tcp://MQTT安装的服务器地址:MQTT定义的端口号
    public String HOST = MQTTConst.HOST;
    //定义MQTT的ID，可以在MQTT服务配置中指定
    private String clientLd =  "1";

    private MqttClient client;
    private String userName = "server_to_send";    //非必须
    private String passWord = MQTTConst.PASSWORD;  //非必须

    private  MqttMessage message;

    /**
     * 构造函数
     * @throws MqttException
     */
    public ServerMQTT() throws MqttException {
        // MemoryPersistence设置clientLd的保存形式，默认为以内存保存
        client = new MqttClient(HOST, clientLd, new MemoryPersistence());

        //初始化会话设置
        options = new MqttConnectOptions();
        //是否清空session
        options.setCleanSession(false);
        options.setUserName(userName);
        options.setPassword(passWord.toCharArray());
        // 设置超时时间
        options.setConnectionTimeout(10);
        // 设置会话心跳时间
        options.setKeepAliveInterval(20);

        //初始化消息设置
        message = new MqttMessage();
        message.setQos(2);  //保证消息能到达一次
        message.setRetained(true);
    }

    /**
     *  用来连接服务器
     * @return
     */
    private MqttTopic connect(String topicName) {
        try {
            client.setCallback(new PushCallback());
            client.connect(options);
            return client.getTopic(topicName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param topic
     * @param message
     * @throws MqttPersistenceException
     * @throws MqttException
     */
    public void publish(MqttTopic topic , MqttMessage message) throws MqttPersistenceException,
            MqttException {
        MqttDeliveryToken token = topic.publish(message);

        token.waitForCompletion();
        System.out.println("message is published completely! "
                + token.isComplete());
    }

    public  void sendMessage(String topicName,String clieId,String msg)throws Exception{
        MqttTopic topic = connect(topicName);
        String str ="{\"clieId\":\""+clieId+"\",\"mag\":\""+msg+"\"}";
        message.setPayload(str.getBytes());
        try{
            publish(topic , message);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //断开连接
            client.disconnect();
        }
    }


}

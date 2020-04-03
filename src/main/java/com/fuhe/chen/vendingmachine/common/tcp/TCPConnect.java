package com.fuhe.chen.vendingmachine.common.tcp;

import java.io.DataOutputStream;
import java.net.Socket;

public class TCPConnect {
    private String ip ;
    private int port ;

    public TCPConnect(){

    }

    public TCPConnect(String ip,int port){
        this.ip=ip;
        this.port=port;
    }

    public void send(String message){
        // 连接到服务器
        byte[] command = message.getBytes();
        try {
            Socket socket = new Socket(ip, port);//创建Socket类对象
            try{
                DataOutputStream out = new DataOutputStream(socket
                        .getOutputStream());// 向服务器端发送信息的DataOutputStream
                out.write(command);
            }
          finally {
                socket.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

package com.qianjin.support;

public class MessageSender {

    public static int send(Order order){
        LeanCloud.instance().save(order);
        return 0;
    }
}

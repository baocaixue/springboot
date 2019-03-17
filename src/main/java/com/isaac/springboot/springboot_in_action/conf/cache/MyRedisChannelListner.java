package com.isaac.springboot.springboot_in_action.conf.cache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

public class MyRedisChannelListner implements MessageListener {
    public static final Log LOG = LogFactory.getLog(MyRedisChannelListner.class);
    @Override
    public void onMessage(Message message, byte[] bytes) {
        byte[] channel = message.getChannel();
        byte[] body = message.getBody();
        try{
            String content = new String(body, "UTF-8");
            String ch = new String(channel, "UTF-8");
            LOG.info("get " + content + " from " +ch);
        }catch (Exception e){
            LOG.error("redis channel listener error happen : " + e.getMessage());
            e.printStackTrace();
        }
    }
}

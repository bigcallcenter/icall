package com.bigcallcenter.icall.redis;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.oxm.XmlMappingException;
import org.springframework.oxm.castor.CastorMarshaller;


public class SubMessageListener extends MessageListenerAdapter {
	public void onMessage(Message message, byte[] pattern) {
		String messageStr = message.toString();
		String queueName="";
		try {
			queueName= new String(message.getChannel(),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		if ("requestQueue:queue".equals(queueName)) { 
			
		}
	}
}

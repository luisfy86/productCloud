package com.luis.product.service.impl;

import com.luis.product.model.Product;
import com.luis.product.model.ProductUpdMsg;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.stereotype.Component;

@Component
public class ProductMsgProducer {
    @Autowired
    JmsTemplate prodUpdTemplate;

    //@Value("${jms.ProductTopic}")
    private String productTopic = "ProductT";

    @Bean
    public MessageConverter jacksonJmsMessageConverter(){
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    public void sendUpdate(Product product, boolean isDelete){
        ProductUpdMsg msg = new ProductUpdMsg();
        msg.setProduct(product);
        msg.setDelete(isDelete);
        prodUpdTemplate.convertAndSend(productTopic, msg);
    }
}

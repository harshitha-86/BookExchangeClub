package com.goodbookclub.bookclub.services.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TextMessageListener {

    @JmsListener(destination = "text.messagequeue")
    public void onMessage(String msg){
        log.info(msg);
    }
}

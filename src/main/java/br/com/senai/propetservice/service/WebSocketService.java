package br.com.senai.propetservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    // public void sendToUser(TokenAu) {
    //     simpMessagingTemplate.convertAndSendToUser(user, destination, payload);
    // }
}

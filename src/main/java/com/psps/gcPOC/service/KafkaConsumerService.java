package com.psps.gcPOC.service;

import com.psps.gcPOC.GcPocApplication;
import com.psps.gcPOC.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private final UserService userService;

    public KafkaConsumerService(UserService userService) {
        this.userService = userService;
    }
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaListener(topics = "user-topic", groupId = "my-group")
    public void consume(String message) {
        logger.info("Consumed message: " + message);

        String[] parts = message.split(": ");
        if (parts.length > 1) {
            String email = parts[1];

            User user = new User();
            user.setEmail(email);
            userService.saveUser(user);
            logger.info("User saved to DB: " + email);
        }
    }
}

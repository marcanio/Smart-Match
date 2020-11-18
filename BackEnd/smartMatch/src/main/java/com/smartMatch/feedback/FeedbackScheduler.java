package com.smartMatch.feedback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class FeedbackScheduler {
    private static final Logger log = LoggerFactory.getLogger(FeedbackScheduler.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Autowired
    public FeedbackDBRepository feedbackDBRepository;
    @Autowired
    private JavaMailSender javaMailSender;

    @Scheduled(fixedRate = 60000)//in milliseconds
    public void checkFeedbacks() {
        List<FeedbackDB> results = feedbackDBRepository.findAll();
        if (results.size() > 0) {
            feedbackDBRepository.deleteAll();
            for (FeedbackDB f : results)
            {
                SimpleMailMessage msg = new SimpleMailMessage();
                msg.setTo("jayant7604@gmail.com");
                msg.setSubject("New feedback");
                msg.setText("Text  - " + f.getData() + "\n Severity - " + f.getSeverity());
                javaMailSender.send(msg);
                System.out.println("New mail sent");
                System.out.println("Text  - " + f.getData() + "\n Severity - " + f.getSeverity());
            }
            log.info("Number of feedback fetched:" + results.size());
        }

    }
}

package com.smartMatch.email;

import com.smartMatch.user.Message;
import com.smartMatch.user.User;
import com.smartMatch.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;

@RestController
//@RequestMapping("/feedback")
public class EmailController {

    private com.smartMatch.email.EmailConfiguration emailCfg;

    @Autowired
    public emailRepository emailRepo;

    public EmailController(com.smartMatch.email.EmailConfiguration emailCfg) {
        this.emailCfg = emailCfg;
    }

    /**
     * Saves a new feedback to the databse
     *
     * @param feedback - Feedback that has been added
     * @return - The Net-ID of the user being added.
     */
    @RequestMapping(method = RequestMethod.POST, path = "/feedback/new", produces = "application/json")
    public Message saveFeedback(@RequestBody Feedback feedback) { // usersRepository.save(user.getFirst_name());
//        user.setCode(new CodeGenerator().sevenDigit());
        emailRepo.save(feedback);

        System.out.println("\n" + feedback.getEmail() + " has been successfully saved. \n");
        return new Message("Done");
    }


    @PostMapping
    public void sendFeedback(@RequestBody Feedback feedback,
                             BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationException("Feedback is not valid");
        }

        // Create a mail sender
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.emailCfg.getHost());
        mailSender.setPort(this.emailCfg.getPort());
        mailSender.setUsername(this.emailCfg.getUsername());
        mailSender.setPassword(this.emailCfg.getPassword());

        // Create an email instance
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(feedback.getEmail());
        mailMessage.setTo("smartMatch@feedback.com");
        mailMessage.setSubject("New feedback from " + feedback.getName());
        mailMessage.setText(feedback.getFeedback());

        // Send mail
        mailSender.send(mailMessage);
    }
}

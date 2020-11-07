package com.smartMatch.match;

import com.smartMatch.user.Message;
import com.smartMatch.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is the controller for the male and female class .
 * @author Rishabh bansal
 */

@RestController
public class mfController {

    @Autowired
    UserMaleRepository userMaleRepository;

    @Autowired
    UserFemaleRepository userFemaleRepository;

    @RequestMapping(method = RequestMethod.POST, path = "/users/SaveGender", produces = "application/json")
    /**
     * Saving the user weather for male or female.
     */
    public Message saveUser(@RequestBody User user) { // usersRepository.save(user.getFirst_name());
//        user.setCode(new CodeGenerator().sevenDigit());
//        usersRepository.save(user);

        System.out.println("\n" + user.getEmailaddress() + " has been successfully saved. \n");
        return new Message("Done");
    }
}

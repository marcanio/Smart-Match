package com.smartMatch.user;
import com.smartMatch.code.CodeGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.*;


public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController userController;

    /**
     * Saves a new user into the database.
     *
     * @param user - The user being added.
     * @return - The Net-ID of the user being added.
     */
    @RequestMapping(method = RequestMethod.POST, path = "users/new", produces = "application/json")
    public String saveUser(@RequestBody User user) {
//        user.setCode(new CodeGenerator().sevenDigit());
//        usersRepository.save(user);
//
//        System.out.println("\n" + user.getNetID() + " has been successfully saved. \n");
//        return user.getNetID();
        return null;
    }

}

package com.smartMatch.user;

import com.smartMatch.match.UserFemale;
import com.smartMatch.match.UserFemaleRepository;
import com.smartMatch.match.UserMale;
import com.smartMatch.match.UserMaleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@RestController
public class UserController {
    static {
        System.out.println("\nReady to save new user...\n");
    }

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    public UserRepository usersRepository;
    @Autowired
    public UserMaleRepository usersMaleRepository;
    @Autowired
    public UserFemaleRepository usersFemaleRepository;

    /**
     * Saves a new user into the database.
     *
     * @param user - The user being added.
     * @return - The Net-ID of the user being added.
     */
    @RequestMapping(method = RequestMethod.POST, path = "/users/new", produces = "application/json")
    public Message saveUser(@RequestBody User user) { // usersRepository.save(user.getFirst_name());
        //        user.setCode(new CodeGenerator().sevenDigit());
        usersRepository.save(user);
        System.out.println("\n" + user.getEmailaddress() + " has been successfully saved. \n");
        return new Message("Done");
    }

    /**
     * Gets a list of all the users in the database.
     *
     * @return - A list of all the users in the database.
     */
    @RequestMapping(method = RequestMethod.GET, path = "/users")
    public List<User> getAllUsers() {
        logger.info("Entered into Controller Layer");
        List<User> results = usersRepository.findAll();
        logger.info("Number of Records Fetched:" + results.size());
        return results;
    }

    /**
     * Very important "helper" method. Finds the respective user corresponding to
     * the NetID being entered in.
     *
     * @param email_address - The NetID of the user we are looking for.
     * @return - The user corresponding to the NetID, assuming that it exists.
     */
    @RequestMapping(method = RequestMethod.GET, path = "/user/find/{email_address}")
    //    @GetMapping("/users/{net_id}")
    public User findUserByemail(@PathVariable(value = "email_address") String email_address) {
        logger.info("Entered into Controller Layer");
        List<User> allUsers = usersRepository.findAll(); //findByEmailaddress() Important to change
        User user = new User();
        for (int i = 0; i < allUsers.size(); i++) {
            if (allUsers.get(i).getEmailaddress().equals(email_address)) {
                user = allUsers.get(i);
                break;
            }
        }
        System.out.println("\nUser associated with net-id " + email_address + "is : " + user.getFirstName() + " " + user.getLastName());
        return user;
    }

    /**
     * Primarily for testing purposes. Gets the 'First Name ' column from the respective
     * user.
     *
     * @param email_address - The NetID of the user who we're checking the code from.
     * @return userCode - The code of the user.
     */
    @RequestMapping(method = RequestMethod.GET, path = "/users_email/{email_address}")
    public String getCodeByNetID(@PathVariable("email_address") String email_address) {
        logger.info("Entered into Controller Layer");
        User user = findUserByemail(email_address);
        return user.getFirstName();
    }

    /**
     * When the user enters the code on the app and hits submit, it will both enter
     * the entered_code into the database and check if the entered_code matches the
     * code. If so, then the user is "verified".
     */
    @RequestMapping(method = RequestMethod.POST, path = "users/verify", produces = "application/json", consumes = "application/json")
    public String setUserEnteredCode(@RequestBody Verify verify) {
        List<User> allUsers = usersRepository.findAll();
        User user = new User();
        for (int i = 0; i < allUsers.size(); i++) {
            if (allUsers.get(i).getEmailaddress().equals(verify.emailaddress)) {
                if (allUsers.get(i).getUserPassword().equals(verify.userPassword)) {
                    return "Verified";
                }
                return "Not Verified";
            }
            return "Not Verified";
        }
        return "Not Verified ";
    }

    @RequestMapping(method = RequestMethod.POST, path = "users/verifies")
    public Message verify(@RequestBody Verify verify) {
        List<User> allUsers = usersRepository.findAll();
        User user = new User();
        for (int i = 0; i < allUsers.size(); i++) {
            if (allUsers.get(i).getEmailaddress().equals(verify.emailaddress)) {
                if (allUsers.get(i).getUserPassword().equals(verify.userPassword)) {
                    return new Message("Verified");
                }
                //                return new Message("Not Verified");
                return null;
            }
            //            return new Message("Not Verified");
            return null;
        }
        //        return "Not Verified ";
        //        return new Message("Not Verified");
        return null;
    }

    /**
     * Gets a list of matches of current user i
     *
     * @return - A list of matches from the database.
     */
    @RequestMapping(method = RequestMethod.GET, path = "/user/match/{email_address}")
    public List<Serializable> getMatches(@PathVariable("email_address") String email_address) {
        logger.info("Entered into Controller Layer");
        int GAP = 5;//get matches select +/- gap from user score
        List<UserMale> allUsersMale = usersMaleRepository.findAll();
        List<UserFemale> allUsersFemale = usersFemaleRepository.findAll();
        List<Serializable> matches = new LinkedList<>();
        int check = 0;
        Serializable user = null;
        for (UserMale u : allUsersMale) {
            if (u.getEmailaddress().toLowerCase().equals(email_address.toLowerCase())) {
                check = 1;
                user = u;
                break;
            }
        }
        for (UserFemale u : allUsersFemale) {
            if (u.getEmailaddress().toLowerCase().equals(email_address.toLowerCase())) {
                check = -1;
                user = u;
                break;
            }
        }
        if (check == 0) {
            logger.info("Number of Records Fetched: 0");
            return matches;
        }
        if (check == 1) {
            UserMale uu = (UserMale) user;
            int hScore = uu.getUserscore() + GAP;
            int lScore = uu.getUserscore() - GAP;
            for (UserFemale u2 : allUsersFemale) {
                if ((u2.getUserscore() >= lScore) && (u2.getUserscore() <= hScore)) {
                    matches.add(u2);
                }
            }
        } else {
            UserFemale uu = (UserFemale) user;
            int hScore = uu.getUserscore() + GAP;
            int lScore = uu.getUserscore() - GAP;
            for (UserMale u2 : allUsersMale) {
                if ((u2.getUserscore() >= lScore) && (u2.getUserscore() <= hScore)) {
                    matches.add(u2);
                }
            }
        }
        logger.info("Number of Records Fetched:" + matches.size());
        return matches;
    }
}

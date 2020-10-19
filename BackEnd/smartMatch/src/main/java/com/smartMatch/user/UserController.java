package com.smartMatch.user;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class UserController {
    static {
        System.out.println("\nReady to save new user...\n");
    }

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    public UserRepository usersRepository;

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
        System.out.println(
                "\nUser associated with net-id " + email_address + "is : " + user.getFirstName() + " " + user.getLastName());

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
    public String getFirstNameByEmail(@PathVariable("email_address") String email_address) {
        logger.info("Entered into Controller Layer");
        User user = findUserByemail(email_address);
        return user.getFirstName();
    }

        /**
     * When the user enters the code on the app and hits submit, it will both enter
     * the entered_code into the database and check if the entered_code matches the
     * code. If so, then the user is "verified".
     *
     */
//    @RequestMapping(method = RequestMethod.POST, path = "users/verify", produces = "application/json", consumes = "application/json")
//    public String setUserEnteredCode(@RequestBody Verify verify) {
//
//        List<User> allUsers = usersRepository.findAll();
//        User user = new User();
//        for (int i = 0; i < allUsers.size(); i++) {
//            if (allUsers.get(i).getEmailaddress().equals(verify.emailaddress)) {
//                if (allUsers.get(i).getUserPassword().equals(verify.userPassword)) {
//                    return "Verified";
//                }
//                return "Not Verified";
//            }
//        }
//        return "Not Verified";
//    }
        @RequestMapping(method = RequestMethod.POST, path = "users/verifies")
        public Message verify(@RequestBody Verify verify){
            List<User> allUsers = usersRepository.findAll();
            User user = new User();
            for (int i = 0; i < allUsers.size(); i++) {
                if (allUsers.get(i).getEmailaddress().equals(verify.emailaddress)) {
                    if (allUsers.get(i).getUserPassword().equals(verify.userPassword)) {
                        return new Message(allUsers.get(i).getId().toString());

                    }
//                return new Message("Not Verified");
//                return null;
                    return null;
                }
            }
//        return new Message("Not Verified");
//        return null;
            return null;
        }
    @RequestMapping(method = RequestMethod.POST, path = "users/verify")
    public Message verify_U(@RequestBody Verify verify){
        List<User> allUsers = usersRepository.findAll();
        User user = new User();
        for (int i = 0; i < allUsers.size(); i++) {
            if (allUsers.get(i).getEmailaddress().equals(verify.emailaddress)) {
                if (allUsers.get(i).getUserPassword().equals(verify.userPassword)) {
                    return new Message("Verified");
                }
//                return new Message("Not Verified");
//                return null;
                return new Message("Not Verified1");
            }
        }
//        return new Message("Not Verified");
//        return null;
        return new Message("Not Verified2");
    }
    /**
     * This function is used to update the database of the user table with the updated field.
     * So like in this The first name of the user is being changed
     *
     * @param first_name - The emailID of the user who we're checking the code from.
     * @return The new updated name.
     */

    @RequestMapping(method = RequestMethod.PUT, path = "users/update/name/{email_address}/{new_first_name}")
    public Message updateName(@PathVariable("email_address") String email_address, @PathVariable("new_first_name") String first_name){
        User user = findUserByemail(email_address);
        user.setFirstName(first_name);
        usersRepository.save(user);
        return new Message("Named changed to: " +user.getFirstName());
    }

    /**
     * This function is used to update the database of the user table with the updated field.
     * So like in this The Phone_number of the user is being changed
     *
     * @param phone_number - The emailID of the user who we're checking the code from.
     * @return The new updated phone_number.
     */

    @RequestMapping(method = RequestMethod.PUT, path = "users/update/phone_number/{email_address}/{new_phone_number}")
    public Message updatePhoneNumber(@PathVariable("email_address") String email_address, @PathVariable("new_phone_number") String phone_number){
        User user = findUserByemail(email_address);
        user.setPhoneNumber(phone_number);
        usersRepository.save(user);
        return new Message("Named changed to: " +user.getPhoneNumber());
    }

    /**
     * This function would be used to delete the user from the databse
     * with the given email_address
     *
     * @param email_address - The emailID of the user who we're checking the code from.
     * @return The Email id that has been deleted.
     */
    @RequestMapping(method = RequestMethod.DELETE, path = "users/delete/{email_address}")
    public Message deleteUser(@PathVariable("email_address") String email_address){
        User user = findUserByemail(email_address);

        usersRepository.delete(user);
        return new Message("User: " + user.getEmailaddress() + " successfully deleted");
    }

}

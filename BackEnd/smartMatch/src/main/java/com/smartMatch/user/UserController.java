

package com.smartMatch.user;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.smartMatch.match.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.Serializable;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * This the the controller for the user class. All the functionality is defined over here.
 * @author Rishabh bansal
 */
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
    @Autowired
    private FileStorageService storageService;

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
    @RequestMapping(method = RequestMethod.GET, path = "/users/findBy_email/{email_address}")
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
     * @param verify - The class for input of pass and email
     * @return Verified or not
     *
     */

    @RequestMapping(method = RequestMethod.POST, path = "users/verifies")
    public Message verify(@RequestBody Verify verify){
        List<User> allUsers = usersRepository.findAll();
        User user = new User();
        for (int i = 0; i < allUsers.size(); i++) {
            if (allUsers.get(i).getEmailaddress().equals(verify.emailaddress)) {
                if (allUsers.get(i).getUserPassword().equals(verify.userPassword)) {
                    return new Message(allUsers.get(i).getId().toString());

                }
                return null;
            }
        }
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
     * @param first_name - The first_name of the user who we're checking the code from.
     * @param email_address - The emailID of the user who we're checking the code from.
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
     * @param email_address - The emailID of the user who we're checking the code from.
     * @return The new updated phone_number.
     */

    @RequestMapping(method = RequestMethod.PUT, path = "users/update/phone_number/{email_address}/{new_phone_number}")
    public Message updatePhoneNumber(@PathVariable("email_address") String email_address, @PathVariable("new_phone_number") String phone_number){
        User user = findUserByemail(email_address);
        user.setPhoneNumber(phone_number);
        usersRepository.save(user);
        return new Message("Phone Number changed to: " +user.getPhoneNumber());
    }
    /**
     * This function is used to update the database of the user table with the updated field.
     * So like in this The Gender of the user is being changed
     *
     * @param gender - The emailID of the user who we're checking the code from.
     * @param email_address - The emailID of the user who we're checking the code from.
     * @return The new updated phone_number.
     */

    @RequestMapping(method = RequestMethod.PUT, path = "users/update/gender/{email_address}/{new_gender}")
    public Message updateGender(@PathVariable("email_address") String email_address, @PathVariable("new_gender") String gender){
        User user = findUserByemail(email_address);
        user.setGender(gender);
        usersRepository.save(user);
        return new Message("Gender changed to: " +user.getGender());
    }

    /**
     * This function is used to update the database of the user table with the updated field.
     * So like in this The password of the user is being changed
     *
     * @param password - The emailID of the user who we're checking the code from.
     * @param email_address - The emailID of the user who we're checking the code from.
     * @return The new updated phone_number.
     */

    @RequestMapping(method = RequestMethod.PUT, path = "users/update/password/{email_address}/{new_password}")
    public Message updatePass(@PathVariable("email_address") String email_address, @PathVariable("new_password") String password){
        User user = findUserByemail(email_address);
        user.setUserPassword(password);
        usersRepository.save(user);
        return new Message("Password changed to: " +user.getUserPassword());
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

    /**
     * This function would be used to ge the ID associated with the user.
     *
     * @param email_address - The NetID of the user who we're checking the code from.
     * @return ID - The code of the user.
     */
    @RequestMapping(method = RequestMethod.GET, path = "/users_ID/{email_address}")
    public String getIDByEmail(@PathVariable("email_address") String email_address) {
        logger.info("Entered into Controller Layer");
        User user = findUserByemail(email_address);
        return user.getId().toString();
    }

    /**
     * This function would be used to ge the ID associated with the user.
     *
     * @param email - The NetID of the user who we're checking the code from.
     * @return ID - The code of the user.
     */
    @RequestMapping(method = RequestMethod.GET, path = "/users_Email/{email_address}")
    public String getEmailByID(@PathVariable("email_address") String email) {
        logger.info("Entered into Controller Layer");
        User user = findUserByemail(email);
//        User user = findUserByID(ID);
        if(user.getId()==null)
            return "Not a valid Email, Please enter a Valid User Email_ID";
        else
            return user.getId().toString();
    }
    /**
     * This is a very important method.
     * This wold return the user associated with the particular ID.
     *
     * @param id - The NetID of the user we are looking for.
     * @return - The user corresponding to the NetID, assuming that it exists.
     */
    @RequestMapping(method = RequestMethod.GET, path = "/user/findBy_ID/{id}")
    public User findUserByID(@PathVariable(value = "id") String id) {
        logger.info("Entered into Controller Layer");
        List<User> allUsers = usersRepository.findAll();
        User user = new User();

        for (int i = 0; i < allUsers.size(); i++) {
            if (allUsers.get(i).getId().toString().equals(id)) {
                user = allUsers.get(i);
                break;
            }
        }
        System.out.println(
                "\nUser associated with Id " + id + "is : " + user.getFirstName() + " " + user.getLastName());

        return user;
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

    @GetMapping("user/{id}/image")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        FileDB fileDB = storageService.getFile(id);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getId() + "\"").body(fileDB.getData());
    }

    @PostMapping(value = {"user/{id}/image"}, consumes = {"multipart/form-data"})
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable String id) {
        String message = "";
        try {
            //   if (file.getContentType().split("/")[0].equals("image")) {
            storageService.store(file, id);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
           /* } else {
                message = "File is not an image";
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
            }*/
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }


}

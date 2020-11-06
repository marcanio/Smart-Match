package com.smartMatch.user;

import com.smartMatch.match.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @RequestMapping(method = RequestMethod.GET, path = "/users/fill")
    public void fill() {
        usersMaleRepository.save(new UserMale("Jayant Shah", "Bio1", 211l, "smit@mail.com", "male", 35));
        usersMaleRepository.save(new UserMale("Rishabh Ban", "Bio2", 212l, "bond@mail.com", "male", 40));
        usersMaleRepository.save(new UserMale("Eric Marc", "Bio3", 213l, "dou@mail.com", "male", 45));
        usersMaleRepository.save(new UserMale("Vivek Bengre", "Bio4", 214l, "ng@mail.com", "male", 30));
        usersMaleRepository.save(new UserMale("Suraj Pariyar", "Bio5", 215l, "gee@mail.com", "male", 25));
        usersFemaleRepository.save(new UserFemale("Priyanka Chopra", "Bio1", 211l, "smit1@mail.com", "male", 35));
        usersFemaleRepository.save(new UserFemale("Katrina Kaif", "Bio2", 212l, "bond1@mail.com", "male", 40));
        usersFemaleRepository.save(new UserFemale("Aditi Rao", "Bio3", 213l, "dou1@mail.com", "male", 45));
        usersFemaleRepository.save(new UserFemale("Sonam Kapoor", "Bio4", 214l, "ng1@mail.com", "male", 30));
        usersFemaleRepository.save(new UserFemale("Alia Bhatt", "Bio5", 215l, "gee1@mail.com", "male", 25));
       /*

           public UserFemale(String firstName, String userbio, Long id, String emailaddress, String gender, int userscore) {
        this.firstName = firstName;
        this.userbio = userbio;
        this.id = id;
        this.emailaddress = emailaddress;
        this.gender = gender;
        this.userscore = userscore;
    }
        */
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

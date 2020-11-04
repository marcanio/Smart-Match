package com.smartMatch.matches;

import com.smartMatch.user.Message;
import com.smartMatch.user.User;
import com.smartMatch.user.Verify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controller for the Match Class, It is used for having functionality for the match Class.
 */
@RestController
public class MatchController {

    static {
        System.out.println("\n Ready to save new match...");
    }

    private final Logger logger = LoggerFactory.getLogger(MatchController.class);
    @Autowired
    public MatchRepository matchRepo;

    /**
     * Saves a new user with given matches.
     *
     * @param match - The user being added.
     * @return - The User with the saved matches
     */
    @RequestMapping(method = RequestMethod.POST, path = "/matches/new", produces = "application/json")
    public Message addNewMatch(@RequestBody Matches match) {
        matchRepo.save(match);
        System.out.println("\n" + match.toString() + "\n");
//        return ("s");
        return new Message("Match saved for " + match.getEmailAddress() + ": " + match.getMatches()+ " with a quiz score of " + match.getQuizScore());
//        return "Match saved for " + match.getEmailAddress() + ": " + match.getMatches();
    }

//    /**
//     * This function will append the new matches for the user
//     *
//     * @param match - The user being added.
//     * @return - The user with the old and appended matches
//     */
    @RequestMapping(method = RequestMethod.POST, path = "matches/append")
    public String appendNewMatch(@RequestBody com.smartMatch.matches.Matches match) {
        com.smartMatch.matches.Matches matchToUpdate = getByEmailId(match.getEmailAddress());

        matchToUpdate.addMatch(match.getMatches());
        matchRepo.save(matchToUpdate);

        return "New match for " + match.getEmailAddress() + ": " + match.getMatches();
    }

    /**
     * Will be used to display all the user with there matches from the database
     *
     * @return - All the users with their matches in the database.
     */
    @RequestMapping(method = RequestMethod.GET, path = "/matches")
    public List<com.smartMatch.matches.Matches> getAllMatches() {
        logger.info("Entered into Controller Layer");
        List<com.smartMatch.matches.Matches> results = matchRepo.findAll();
        logger.info("Number of Records Fetched: " + results.size());
        return results;
    }

    /**
     * This function will be used to display all the matches for the given users
     *
     * @param email_address - The user being added.
     * @return - The respective user with his matches
     */
    @RequestMapping(method = RequestMethod.GET, path = "/matches/{email_address}")
    public com.smartMatch.matches.Matches getMatchesByEmailId(@PathVariable("email_address") String email_address) {
        logger.info("Entered into Controller Layer");
        com.smartMatch.matches.Matches result = getByEmailId(email_address);

        System.out.println("\n Matches for " + result.getEmailAddress() + ": [" + result.getMatches() + "]");
        return result;
    }

//    @RequestMapping(method = RequestMethod.GET, path = "/matches/{email_address}")
//    public String getMatchesByEmailIdS(@PathVariable("email_address") String email_address) {
//        logger.info("Entered into Controller Layer");
//        com.smartMatch.matches.Matches result = getByEmailId(email_address);
//
//        return(result.getMatches());
////        return result;
//    }

    public com.smartMatch.matches.Matches getByEmailId(String email_address) {
        List<com.smartMatch.matches.Matches> allMatches = matchRepo.findAll();
        com.smartMatch.matches.Matches userToReturn = new com.smartMatch.matches.Matches();

        for (int i = 0; i < allMatches.size(); i++) {
            if (allMatches.get(i).getEmailAddress().equals(email_address)) {
                userToReturn = allMatches.get(i);
                break;
            }
        }

        return userToReturn;
    }
}

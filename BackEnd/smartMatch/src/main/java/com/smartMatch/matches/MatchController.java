package com.smartMatch.matches;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String addNewMatch(@RequestBody Matches match) {
        matchRepo.save(match);
        System.out.println("\n" + match.toString() + "\n");
        return "Match saved for " + match.getEmailAddress() + ": " + match.getMatches();
    }

    /**
     * This function will append the new matches for the user
     *
     * @param match - The user being added.
     * @return - The user with the old and appended matches
     */
    @RequestMapping(method = RequestMethod.POST, path = "matches/{net_id}")
    public String appendNewMatch(@RequestBody com.smartMatch.matches.Matches match) {
        com.smartMatch.matches.Matches matchToUpdate = getByNetId(match.getEmailAddress());

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
     * @param net_id - The user being added.
     * @return - The respective user with his matches
     */

    @RequestMapping(method = RequestMethod.GET, path = "/matches/{net_id}")
    public com.smartMatch.matches.Matches getMatchesByNetId(@PathVariable("net_id") String net_id) {
        logger.info("Entered into Controller Layer");
        com.smartMatch.matches.Matches result = getByNetId(net_id);

        System.out.println("\n Matches for " + result.getEmailAddress() + ": [" + result.getMatches() + "]");
        return result;
    }

    public com.smartMatch.matches.Matches getByNetId(String net_id) {
        List<com.smartMatch.matches.Matches> allMatches = matchRepo.findAll();
        com.smartMatch.matches.Matches userToReturn = new com.smartMatch.matches.Matches();

        for (int i = 0; i < allMatches.size(); i++) {
            if (allMatches.get(i).getEmailAddress().equals(net_id)) {
                userToReturn = allMatches.get(i);
                break;
            }
        }

        return userToReturn;
    }
}

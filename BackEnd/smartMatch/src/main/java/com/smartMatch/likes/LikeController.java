package com.smartMatch.likes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LikeController {
    /*
     * Adding comment to test CICD
     * Another comment
     */

    static {
        System.out.println("\nCyFire: Ready to save new like...");
    }

    private final Logger logger = LoggerFactory.getLogger(LikeController.class);
    @Autowired
    LikeRepository likeRepo;

    @RequestMapping(method = RequestMethod.POST, path = "/likes/new", produces = "appication/json")
    public String addNewLike(@RequestBody Likes like) {
//        likeRepo.save(like);
        System.out.println("\n" + like.toString() + "\n");
        return "Like saved for: " + like.getNet_id() + ": " + like.getLikes();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/likes")
    public List<Likes> getAllLikes() {
        logger.info("Entered into Controller Layer");
//        List<Likes> results = likeRepo.findAll();
//        logger.info("Number of Records Fetched: " + results.size());
//        return results;
        return null;
    }

    }



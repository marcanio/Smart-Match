package com.smartMatch.matches;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
/**
 * Service for the match Class.
 */
public class matchService {

    @Autowired
    private MatchRepository matchRepository;

    public List<Matches> getMatchList() {
        return matchRepository.findAll();
    }
}

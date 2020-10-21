package com.smartMatch.match;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Steven Marshall Sheets
 */
public class UserMatchService {
    @Autowired
    private UserMaleRepository userMaleRepository;
    @Autowired
    private UserFemaleRepository userFemaleRepository;

    public List<UserMale> getUserMaleList() {
        return userMaleRepository.findAll();
    }

    public List<UserFemale> getUserFemaleList() {
        return userFemaleRepository.findAll();
    }
}


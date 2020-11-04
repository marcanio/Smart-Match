package com.smartMatch.user;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Rishabh Bansal
 *Service class for the user.
 */
public class UserService {
    @Autowired

    private UserRepository userRepository;

    public List<User> getUserList() {
        return userRepository.findAll();
    }
}


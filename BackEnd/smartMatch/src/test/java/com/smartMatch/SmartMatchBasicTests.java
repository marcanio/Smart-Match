package com.smartMatch;

//import org.junit.jupiter.api.Test;
import com.smartMatch.user.Verify;
import org.springframework.boot.test.context.SpringBootTest;


import com.smartMatch.matches.Matches;
import com.smartMatch.matches.MatchController;
import com.smartMatch.matches.MatchRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest
public class SmartMatchBasicTests {
    @InjectMocks
    MatchController userController;

    @Autowired
    @Mock
    MatchRepository userRepository;


    @Test
    public void contextLoads() {
        assertThat(userController).isNotNull();
        assertThat(userController.usersRepository).isNotNull();
    }


}

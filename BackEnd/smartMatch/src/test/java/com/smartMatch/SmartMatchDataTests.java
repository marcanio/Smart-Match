package com.smartMatch;

import com.smartMatch.user.User;
import com.smartMatch.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
class SmartMatchDataTests {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void addingUser() {
        User user = new User();
        user.setLastName("69869867");
        entityManager.persist(user);
        entityManager.flush();
        assertTrue(userRepository.findAll().contains(user));
    }
}

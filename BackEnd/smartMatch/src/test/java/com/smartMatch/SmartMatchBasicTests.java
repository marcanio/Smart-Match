package com.smartMatch;

import com.smartMatch.user.User;
import com.smartMatch.user.UserController;
import com.smartMatch.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class SmartMatchBasicTests {
    @Autowired
    private UserController controller;



    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
        assertThat(controller.usersFemaleRepository).isNotNull();
        assertThat(controller.usersMaleRepository).isNotNull();
        assertThat(controller.usersRepository).isNotNull();
    }


}

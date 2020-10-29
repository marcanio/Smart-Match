package com.smartMatch;

import com.smartMatch.user.User;
import com.smartMatch.user.UserRepository;
import com.smartMatch.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class SmartMatchServiceTests {

    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;

    @Before
    public void setUp() {
        User alex = new User();
        alex.setFirstName("Alex");
        List<User> list = new ArrayList<>();
        list.add(alex);
        Mockito.when(userRepository.findAll()).thenReturn(list);
    }

    @Test
    public void mockedFindAll() {
        String name = "Alex";
        User found = userService.getUserList().get(0);
        assertThat(found.getFirstName()).isEqualTo(name);
    }

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public UserService userService() {
            return new UserService();
        }
    }
}

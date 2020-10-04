package com.smartMatch;

//import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import com.smartMatch.user.User;
import com.smartMatch.user.UserController;
import com.smartMatch.user.UserRepository;
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

//@SpringBootTest
//class SmartMatchApplicationTests {
//
//	@Test
//	void contextLoads() {
//	}

//}
//@SpringBootTest
public class SmartMatchApplicationTests {
	@InjectMocks
	UserController userController;

	@Autowired
	@Mock
	UserRepository userRepository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findUserByemailTest() {
		User rishabh = new User("Rishabh", "Bansal", "641-895-0122", "smsheets","Male","CS309isfun",19);
		userController.saveUser(rishabh);

		when(userController.usersRepository.findUserByemail("smsheets")).thenReturn(rishabh);

		assertEquals(rishabh, userController.usersRepository.findUserByemail("smsheets"));
	}
	@Test
	public void getAllUsersTest() {
		List<User> userList = new ArrayList<User>();

		User rishabh = new User("Rishabh", "Bansal", "703-8269214", "rbansal@iastate.edu", "Male","CS309isfun", 19);

		User eric = new User("Eric", "Marcaino", "515-204-9214", "emarcaino@iastate.edu", "Male","iameric", 20);


		User jayant = new User("Jayant", "Shah", "515-242-5445", "jshah@iastate.edu", "Male","iamjayant", 21);


		User suraj = new User("Suraj", "Shah", "515-191-2003", "sshah@iastate.edu", "Male","iamsuraj", 22);


//		addToUserList(userList, rishabh, eric, jayant, suraj);
//
//		saveAllTheUsers(rishabh, eric, jayant, suraj);

		when(userRepository.findAll()).thenReturn(userList);

		List<User> theUsers = userRepository.findAll();

		assertEquals(4, theUsers.size());
		verify(userRepository, times(1)).findAll();
	}

//	@Test
//	void contextLoads() {
//	}

}

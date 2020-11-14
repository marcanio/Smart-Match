package com.smartMatch;

//import org.junit.jupiter.api.Test;
import com.smartMatch.user.Verify;
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


/**
 * Application Test for the User class
 *
 * @author Rishabh bansal
 */
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

//	@Test
//	public void findUserByemailTest() {
//		User rishabh = new User("Rishabh", "Bansal", "641-895-0122", "smsheets","Male","CS309isfun",19);
//		userController.saveUser(rishabh);
//
//		when(userController.usersRepository.findUserByemail("smsheets")).thenReturn(rishabh);
//
//		assertEquals(rishabh, userController.usersRepository.findUserByemail("smsheets"));
//	}


	@Test
	public void getAllUsersTest() {
		List<User> userList = new ArrayList<User>();

		User rishabh = new User("Rishabh", "Bansal", "703-8269214", "rbansal@iastate.edu", "Male","CS309isfun", 19);

		User eric = new User("Eric", "Marcaino", "515-204-9214", "emarcaino@iastate.edu", "Male","iameric", 20);


		User jayant = new User("Jayant", "Shah", "515-242-5445", "jshah@iastate.edu", "Male","iamjayant", 21);


		User suraj = new User("Suraj", "Shah", "515-191-2003", "sshah@iastate.edu", "Male","iamsuraj", 22);


		addToUserList(userList, rishabh, eric, jayant, suraj);

		saveAllTheUsers(rishabh, eric, jayant, suraj);

		when(userRepository.findAll()).thenReturn(userList);

		List<User> theUsers = userRepository.findAll();

		assertEquals(4, theUsers.size());

		verify(userRepository, times(1)).findAll();
//		System.out.println(userRepository.findAll().contains(rishabh));
	}
//s
	@Test
	public void verifyEmailAndPasswordTest(){
		List<User> userList = new ArrayList<User>();
		User rishabh = new User("Rishabh", "Bansal", "703-8269214", "rbansal@iastate.edu", "Male","CS309isfun", 19);
		Verify verify_test1 = new Verify("rbansal@iastate.edu","CS309isfun");
		userList.add(rishabh);
		when(userRepository.findAll()).thenReturn(userList);
		userController.saveUser(rishabh);
		List<User> theUsers = userRepository.findAll();
//		assertEquals(true, theUsers.contains(rishabh));
		assertEquals("CS309isfun", theUsers.get(0).getUserPassword());
		assertEquals("Verified", userController.verify_U(verify_test1).getMessage());

		Verify verify_test2 = new Verify("rbansal@iastate.edu","CS309fun");

		assertEquals("Not Verified1", userController.verify_U(verify_test2).getMessage());

		Verify verify_test3 = new Verify("rbansal@iastate.","CS309isfun");

		assertEquals("Not Verified2", userController.verify_U(verify_test3).getMessage());

	}


	@Test
	public void getFirstNameByEmailTest(){
		List<User> userList = new ArrayList<User>();
		User rishabh = new User("Rishabh", "Bansal", "703-8269214", "rbansal@iastate.edu", "Male","CS309isfun", 19);
		userList.add(rishabh);
		when(userRepository.findAll()).thenReturn(userList);
		userController.saveUser(rishabh);
		//List<User> theUsers = userRepository.findAll();
		assertEquals("Rishabh", userController.getFirstNameByEmail("rbansal@iastate.edu"));

	}

	/**
	 * New For demo 3
	 */
	@Test
	public void getPhoneNumberByEmailTest(){
		List<User> userList = new ArrayList<User>();
		User rishabh = new User("Rishabh", "Bansal", "7038269214", "rbansal@iastate.edu", "Male","CS309isfun", 19);
		userList.add(rishabh);
		when(userRepository.findAll()).thenReturn(userList);
		userController.saveUser(rishabh);
		//List<User> theUsers = userRepository.findAll();
		assertEquals("7038269214", userController.getNumberByEmail("rbansal@iastate.edu"));

	}

	private void saveAllTheUsers(User rishabh, User eric, User jayant, User suraj) { // Do not need userController as a parameter because it is a global variable.
		userController.saveUser(rishabh);
		userController.saveUser(eric);
		userController.saveUser(jayant);
		userController.saveUser(suraj);
	}

	private void addToUserList(List<User> userList, User rishabh, User eric, User jayant, User suraj) {
		userList.add(rishabh);
		userList.add(eric);
		userList.add(jayant);
		userList.add(suraj);
	}


//	@Test
//	void contextLoads() {
//	}

}

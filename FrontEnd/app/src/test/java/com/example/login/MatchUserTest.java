package com.example.login;

import org.junit.Test;

import java.lang.reflect.Field;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MatchUserTest {

    @Test
    public void userGetter() throws NoSuchFieldException, IllegalAccessException{
        String firstName = "Eric";
         int id =1;
         String emailaddress = "eric@gmail.com";
         String gender = "male";
         int userscore = 32;

         MatchUser myObjectUnderTest = new MatchUser(firstName, id, emailaddress, gender, userscore);

        final Field field = myObjectUnderTest.getClass().getDeclaredField("firstName");
        field.setAccessible(true);
        field.set(myObjectUnderTest, "Eric");
        //Options
        final Field fieldA = myObjectUnderTest.getClass().getDeclaredField("id");
        fieldA.setAccessible(true);
        fieldA.set(myObjectUnderTest, 1);

        final Field fieldB = myObjectUnderTest.getClass().getDeclaredField("emailaddress");
        fieldB.setAccessible(true);
        fieldB.set(myObjectUnderTest, "eric@gmail.com");

        final Field fieldC = myObjectUnderTest.getClass().getDeclaredField("gender");
        fieldC.setAccessible(true);
        fieldC.set(myObjectUnderTest, "male");

        final Field fieldD = myObjectUnderTest.getClass().getDeclaredField("userscore");
        fieldD.setAccessible(true);
        fieldD.set(myObjectUnderTest, 32);

        //When
        final String result = myObjectUnderTest.getFirstName();
        final int resultA = myObjectUnderTest.getId();
        final String resultB = myObjectUnderTest.getEmailaddress();
        final String resultC = myObjectUnderTest.getGender();
        final int resultD = myObjectUnderTest.getUserscore();

        //then
        assertThat(result, is("Eric"));
        assertThat(resultA, is(1));
        assertThat(resultB, is("eric@gmail.com"));
        assertThat(resultC, is("male"));
        assertThat(resultD, is(32));

    }

    @Test
    public void setUser() {
        String firstName = "Eric";
        int id =1;
        String emailaddress = "eric@gmail.com";
        String gender = "male";
        int userscore = 32;

        MatchUser user = new MatchUser();
        user.setEmailaddress("eric@gmail.com");
        user.setFirstName("Eric");
        user.setId(1);
        user.setGender("male");
        user.setUserscore(32);

        assertThat("Eric", is(user.getFirstName()));
        assertThat(1, is(user.getId()));
        assertThat("eric@gmail.com", is(user.getEmailaddress()));
        assertThat("male", is(user.getGender()));
        assertThat(32, is(user.getUserscore()));
    }
}

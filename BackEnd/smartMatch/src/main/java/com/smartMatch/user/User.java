package com.smartMatch.user;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Column(name = "first_name")
    @NotFound(action = NotFoundAction.IGNORE)
    private String firstName;

    @Column(name = "last_name")
    @NotFound(action = NotFoundAction.IGNORE)
    private String lastName;

    @Column(name = "phone_number")
    @NotFound(action = NotFoundAction.IGNORE)
    private String phoneNumber;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "email_address")
    @NotFound(action = NotFoundAction.IGNORE)
    private String emailaddress;

    @Column(name = "gender")
    @NotFound(action = NotFoundAction.IGNORE)
    private String gender;

    @Column(name = "user_password")
    @NotFound(action = NotFoundAction.IGNORE)
    private String userPassword;


    @Column(name = "age")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer age;

//    @Column(name = "score")
//    @NotFound(action = NotFoundAction.IGNORE)
//    private Integer score;


    // MAKE A DIDDLY DARN DEFAULT AND CONSTRUCTOR FOR THE MOCKITO TESTS
    public User(String first_name, String last_name, String phone_number, String email_address, String gender,
                String user_password, Integer age) {
        super();
        this.firstName = first_name;
        this.lastName = last_name;
        this.phoneNumber = phone_number;
        this.emailaddress = email_address;
        this.gender = gender;
//        this.score =score;
        this.userPassword = user_password;
//        this.netID = net_id;
        this.age = age;
    }

    public User() {

    }

    @Override
    public String toString() {
        return new ToStringCreator(this).append("first_name: ", this.getFirstName())
                .append("\nlast_name: ", this.getLastName()).append("phone_number: ", this.getPhoneNumber())
                .append("email_address: ", this.getEmailaddress()).append("gender: ", this.getGender()).append("user_password: ", this.getUserPassword())
                .append("age: ", this.getAge()).toString();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phone_number) {
        this.phoneNumber = phone_number;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String email_address) {
        this.emailaddress = email_address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String user_password) {
        this.userPassword = user_password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


}
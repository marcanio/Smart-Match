package com.smartMatch.user;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Column(name = "first_name")
    @NotFound(action = NotFoundAction.IGNORE)
    private String firstName;

    @Column(name = "last_name")
    @NotFound(action = NotFoundAction.IGNORE)
    private String lastName;

    @Column(name = "phone_number")
    @NotFound(action = NotFoundAction.IGNORE)
    private String phoneNumber;

    @Column(name = "gender")
    @NotFound(action = NotFoundAction.IGNORE)
    private String gender;

    @Column(name = "user_password")
    @NotFound(action = NotFoundAction.IGNORE)
    private String userPassword;

//    @Column(name = "classification")
//    @NotFound(action = NotFoundAction.IGNORE)
//    private String classification;

    @Id
    @Column(name = "net_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private String netID;

    @Column(name = "age")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer age;

    @Column(name = "score")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer score;


    @Column(name = "code")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer code;

    @Column(name = "entered_code")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer enteredCode;

    @Column(name = "verified")
    @NotFound(action = NotFoundAction.IGNORE)
    private boolean verified;

    // MAKE A DIDDLY DARN DEFAULT AND CONSTRUCTOR FOR THE MOCKITO TESTS
    public User(String first_name, String last_name, String phone_number, String gender,
                String user_password, String net_id,Integer code, Integer entered_code, Integer age, Integer score, boolean verified) {
        super();
        this.firstName = first_name;
        this.lastName = last_name;
        this.phoneNumber = phone_number;
        this.gender = gender;
        this.score =score;
        this.userPassword = user_password;
        this.code = code;
        this.enteredCode = entered_code;
        this.netID = net_id;
        this.age = age;
        this.verified = verified;
    }

    public User() {

    }

    @Override
    public String toString() {
        return new ToStringCreator(this).append("net_id: ", this.getNetID()).append("first_name: ", this.getFirstName())
                .append("\nlast_name: ", this.getLastName()).append("phone_number: ", this.getPhoneNumber())
                .append("gender: ", this.getGender()).append("user_password: ", this.getUserPassword())
                .append("age: ", this.getAge()).append("\ncode: ", this.getCode())
                .append("entered_code: ", this.getEnteredCode()).append("score: ", this.getScore())
                .append("verified: ", this.isVerified()).toString();
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getEnteredCode() {
        return enteredCode;
    }

    public void setEnteredCode(Integer entered_code) {
        this.enteredCode = entered_code;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String user_password) {
        this.userPassword = user_password;
    }

//    public String getClassification() {
//        return classification;
//    }
//
//    public void setClassification(String classification) {
//        this.classification = classification;
//    }

    public String getNetID() {
        return netID;
    }

    public void setNetID(String net_id) {
        this.netID = net_id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }



}
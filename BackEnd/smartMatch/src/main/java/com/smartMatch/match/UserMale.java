package com.smartMatch.match;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import java.io.Serializable;
/**
 * This is used for creating the databse for the male user.
 */
@Entity
@Table(name = "male_user")
public class UserMale implements Serializable {
    @Column(name = "first_name")
    @NotFound(action = NotFoundAction.IGNORE)
    private String firstName;

    @Column(name = "user_bio")
    @NotFound(action = NotFoundAction.IGNORE)
    private String userbio;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email_address")
    @NotFound(action = NotFoundAction.IGNORE)
    private String emailaddress;

    @Column(name = "gender")
    @NotFound(action = NotFoundAction.IGNORE)
    private String gender;

    @Column(name = "user_score")
    @NotFound(action = NotFoundAction.IGNORE)
    private int userscore;

    public UserMale(String firstName, String userbio, Long id, String emailaddress, String gender, Integer userscore) {
        this.firstName = firstName;
        this.userbio = userbio;
        this.id = id;
        this.emailaddress = emailaddress;
        this.gender = gender;
        this.userscore = userscore;
    }
    @Override
    public String toString() {
        return new ToStringCreator(this).append("first_name: ", this.getFirstName())
                .append("\nuser_bio: ", this.getUserbio()).append("email_address: ", this.getEmailaddress())
                .append("gender: ", this.getGender()).append("user_score: ", this.getUserscore()).toString();
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUserbio() {
        return userbio;
    }

    public void setUserbio(String userbio) {
        this.userbio = userbio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getUserscore() {
        return userscore;
    }

    public void setUserscore(int userscore) {
        this.userscore = userscore;
    }
}

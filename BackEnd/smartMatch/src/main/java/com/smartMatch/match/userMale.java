package com.smartMatch.match;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class userMale implements Serializable {
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

//    @Id
//    @Column(name = "net_id")
//    @NotFound(action = NotFoundAction.IGNORE)
//    private String netID;

    @Column(name = "email_address")
    @NotFound(action = NotFoundAction.IGNORE)
    private String emailaddress;

    @Column(name = "gender")
    @NotFound(action = NotFoundAction.IGNORE)
    private String gender;

    @Column(name = "user_password")
    @NotFound(action = NotFoundAction.IGNORE)
    private String userPassword;

//    @Id
//    @Column(name = "net_id")
//    @NotFound(action = NotFoundAction.IGNORE)
//    private String netID;

//    @Column(name = "classification")
//    @NotFound(action = NotFoundAction.IGNORE)
//    private String classification;


    @Column(name = "age")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer age;

//    @Column(name = "score")
//    @NotFound(action = NotFoundAction.IGNORE)
//    private Integer score;
}

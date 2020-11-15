package com.smartMatch.email;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.core.style.ToStringCreator;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "feedback")
public class Feedback {

    @Column(name = "first_name")
    @NotFound(action = NotFoundAction.IGNORE)
    private String name;

    @Id
    @Column(name = "email_address")
    @NotFound(action = NotFoundAction.IGNORE)
    private  String email;

    @Column(name = "feedback")
    @NotFound(action = NotFoundAction.IGNORE)
    private String feedback;

    public void setSeverity(Integer severity) {
        this.severity = severity;
    }

    @Column(name = "severity")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer severity;

    public Integer getSeverity() {
        return severity;
    }


    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    // MAKE A DIDDLY DARN DEFAULT AND CONSTRUCTOR FOR THE MOCKITO TESTS
    public Feedback(String first_name, String email_address, Integer severity, String feedback) {
        super();
        this.severity = severity;
        this.feedback =feedback;
        this.name =first_name;
        this.email = email_address;

    }

    public Feedback() {

    }

    @Override
    public String toString() {
        return new ToStringCreator(this).append("first_name: ", this.getName())
                .append("email_address: ", this.getEmail()).append("feedback: ", this.getFeedback())
                .append("severity: ", this.getSeverity()).toString();
    }

}



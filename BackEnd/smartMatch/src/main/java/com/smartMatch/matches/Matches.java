package com.smartMatch.matches;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.core.style.ToStringCreator;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "matches")

public class Matches {


    @Id
    @Column(name = "email_address")
    @NotFound(action = NotFoundAction.IGNORE)
    private String emailAddress;

    @Column(name = "matches")
    @NotFound(action = NotFoundAction.IGNORE)
    private String matches;

    public Matches(String emailAddress, String matches) {
        this.emailAddress = emailAddress;
        this.matches = matches;
    }

    public Matches() {
        this.emailAddress = "";
        this.matches = "";
    }

    @Override
    public String toString() {
        return new ToStringCreator(this).append("email_address: ", this.getEmailAddress()).append("matches: ", this.getMatches())
                .toString();
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getMatches() {
        return matches;
    }

    public void setMatches(String matches) {
        this.matches = matches;
    }

    public void addMatch(String newMatch) {
        this.matches += "," + newMatch;
    }

}

package com.smartMatch.matches;
/**
 * Created for taking the entries for email_address and matches
 * @author Rishabh bansal
 */
public class match_append {


    String emailaddress;
    String matches;


    public match_append(String emailaddress, String matches) {
        this.emailaddress = emailaddress;
        this.matches = matches;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getMatches() {
        return matches;
    }

    public void setMatches(String matches) {
        this.matches = matches;
    }

}

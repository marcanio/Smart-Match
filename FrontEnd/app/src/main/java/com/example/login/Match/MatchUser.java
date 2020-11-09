package com.example.login.Match;

public class MatchUser {
    public String firstName;
    public int id;
    public String emailaddress;
    public String gender;
    public int userscore;

    public MatchUser(){

    }

    /**
     * Constructor
     */
    public MatchUser(String firstName, int id, String emailaddress, String gender, int userscore){
        this.firstName = firstName;
        this.id = id;
        this.emailaddress = emailaddress;
        this.gender = gender;
        this.userscore = userscore;
    }

    /**
     * Get users first name
     * @return First name
     */
    public String getFirstName(){
        return firstName;
    }
    /**
     * Set users first name
     * @param firstName - First name of user
     */
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    /**
     * Get users ID
     * @return - user ID
     */
    public int getId(){
        return id;
    }
    /**
     * Set users Unique ID
     * @param id - User ID
     */
    public void setId(int id){
        this.id= id;
    }

    /**
     * Get users Email Address
     * @return - Users Email Address
     */
    public String getEmailaddress(){
        return emailaddress;
    }

    /**
     * Set users Email Address
     * @param emailaddress - Email Address
     */
    public void setEmailaddress(String emailaddress){
        this.emailaddress = emailaddress;
    }

    /**
     * Get users Gender
     * @return - User Gender
     */
    public String getGender(){
        return gender;
    }

    /**
     * Set users Gender
     * @param gender - User Gender
     */
    public void setGender(String gender){
        this.gender= gender;
    }

    /**
     * Get users Quiz score
     * @return - User Quiz Score
     */
    public int getUserscore(){
        return userscore;
    }

    /**
     * Set users Quiz score
     * @param userscore - user Quiz score
     */
    public void setUserscore(int userscore){
        this.userscore = userscore;
    }
}

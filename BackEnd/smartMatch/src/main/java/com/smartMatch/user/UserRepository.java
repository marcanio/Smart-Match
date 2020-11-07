package com.smartMatch.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//
/**
 *Repository for the user class.
 * @author Rishabh bansal
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    /**
     * Finds the user associated with the given emailID
     *
     * @param email_address - The email ID associated with the user.
     * @return - User associated with given NetID
     */
//    User findUserByemail(String email_address);
//    public User findUserByemail(@Param("email") String email_address);

    /**
     * Deletes a user from the database.
     */
    void delete(User user);
}
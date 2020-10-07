package com.smartMatch.match;

import com.smartMatch.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository class for <code>User</code> domain objects All method names are
 * compliant with Spring Data naming conventions so this interface can easily be
 * extended for Spring Data See here:
 * <p>
 * http://static.springsource.org/spring-data/jpa/docs/current/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
 *
 */
@Repository
public interface UserMaleRepository extends JpaRepository<UserMale, String> {
    /**
     * Finds the user associated with the given emailID
     *
     * @param email_address - The netID associated with the user.
     * @return - User associated with given NetID
     */
//    User findUserByEmail(String email_address);
//    public User findByEmail(@Param("email") String email_address);

    /**
     * Deletes a user from the database.
     */
    void delete(UserMale user);
}
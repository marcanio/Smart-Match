package com.smartMatch.email;

import com.smartMatch.matches.Matches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for the Match class.
 * @author Rishabh bansal
 */
@Repository
public interface emailRepository extends JpaRepository<Feedback, String>{
}


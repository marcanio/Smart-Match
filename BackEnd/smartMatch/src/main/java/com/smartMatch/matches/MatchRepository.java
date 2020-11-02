package com.smartMatch.matches;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<com.smartMatch.matches.Matches, Integer> {

}

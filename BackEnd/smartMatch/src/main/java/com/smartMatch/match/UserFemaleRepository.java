package com.smartMatch.match;

import com.smartMatch.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFemaleRepository extends JpaRepository<UserFemale, Long> {

}
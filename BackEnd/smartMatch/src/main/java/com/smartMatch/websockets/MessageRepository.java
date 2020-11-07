package com.smartMatch.websockets;

import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Repository for the Messages of the chat
 *
 * @author Rishabh bansal
 */
public interface MessageRepository extends JpaRepository<Message, Long>{

}
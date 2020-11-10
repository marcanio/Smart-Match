package com.smartMatch.websockets;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


/**
 * Sets up the server for Websocket implementation.
 *
 * @author Rishabh Bansal
 */
@Controller
@ServerEndpoint("/websockets/{net_id}")
public class WebSocketServer {
    // method
    private static MessageRepository msgRepo;

    /*
     * Grabs the MessageRepository singleton from the Spring Application
     * Context.  This works because of the @Controller annotation on this
     * class and because the variable is declared as static.
     * There are other ways to set this. However, this approach is
     * easiest.
     */
    @Autowired
    public void setMessageRepository(MessageRepository repo) {
        msgRepo = repo;  // we are setting the static variable
    }

    // Store all socket session and their corresponding net_id.
    private static Map<Session, String> session_net_id_map = new Hashtable<>();
    private static Map<String, Session> net_id_session_map = new Hashtable<>();

    private final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);


    /**
     * Sends a message to everyone.
     *
     * @param message - Message to be sent.
     * @throws IOException - Potential exception that could occur.
     */
    private void broadcast(String message) {
        session_net_id_map.forEach((session, username) -> {
            try {
                session.getBasicRemote().sendText(message);
            }
            catch (IOException e) {
                logger.info("Exception: " + e.getMessage().toString());
                e.printStackTrace();
            }

        });

    }

    /**
     * What occurs when someone enters the chat application.
     *
     * @param session - The session of the user who has entered the chat
     *                application.
     * @param net_id  - The NetID of the user who wants to chat to others.
     * @throws IOException - Potential exception that could occur.
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("net_id") String net_id) throws IOException {
        logger.info("Entered into Open");

        session_net_id_map.put(session, net_id);

        net_id_session_map.put(net_id, session);

        sendMessageToParticularUser(net_id, getChatHistory());
        String message = "User:" + net_id + " has Joined the Chat";

        broadcast(message);

    }


    /**
     * We are only doing one-on-one conversations, so we will be using the
     * sendMessageToParticularUser method below and force one-on-ones that way. It's
     * sort of a janky way of going about it, but we can just auto-append @net_ids
     * to the start of messages and it can work. It was sick.
     *
     * @param session
     * @param message
     * @throws IOException
     */
    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        /* Handle new messages */
        logger.info("Entered into Message: Got Message:" + message);
        String sender_net_id = session_net_id_map.get(session);

        /*
         * From the client side, just do the following... message = "@" +
         * {receiver_net_id} + " " + message;
         */

//        String receiver_net_id = message.split(" ")[0].substring(1);
//        sendMessageToParticularUser(receiver_net_id, "[DM] " + sender_net_id + ": " + message);
//        sendMessageToParticularUser(sender_net_id, "[DM] " + sender_net_id + ": " + message);
        if (message.startsWith("@")) {
            String destUsername = message.split(" ")[0].substring(1);

            // send the message to the sender and receiver
            sendMessageToParticularUser(destUsername, "[DM] " + sender_net_id + ": " + message);
            sendMessageToParticularUser(sender_net_id, "[DM] " + sender_net_id + ": " + message);

        }

        else { // broadcast
            broadcast(sender_net_id + ": " + message);
        }

        // Saving chat history to repository
        msgRepo.save(new Message(sender_net_id, message));
    }



    @OnClose
    public void onClose(Session session) throws IOException {
        logger.info("Entered into Close");

        String net_id = session_net_id_map.get(session);

        session_net_id_map.remove(session);

        net_id_session_map.remove(net_id);

        String message = net_id + " disconnected. :(";

        broadcast(message);
    }

    /**
     * What occurs should an error be encountered.
     *
     * @param session   - Session causing the trouble.
     * @param throwable - Error
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        logger.info("Entered into Error");
        throwable.printStackTrace();
    }

    /**
     * Sends a message to a particular user.
     *
     * @param net_id  - The NetID that the message will be sent to. The receiver.
     * @param message - The message being sent to the above NetID.
     */
    private void sendMessageToParticularUser(String net_id, String message) {
        try {
            net_id_session_map.get(net_id).getBasicRemote().sendText(message);
        } catch (IOException IOE) {
            logger.info("Exception: " + IOE.getMessage().toString());
            IOE.printStackTrace();
        }
    }

    // Gets the Chat history from the repository
    private String getChatHistory() {
        List<Message> messages = msgRepo.findAll();

        // convert the list to a string
        StringBuilder sb = new StringBuilder();
        if(messages != null && messages.size() != 0) {
            for (Message message : messages) {
                sb.append(message.getUserName() + ": " + message.getContent() + "\n");
            }
        }
        return sb.toString();
    }

}

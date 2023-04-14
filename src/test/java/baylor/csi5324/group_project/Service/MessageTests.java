package baylor.csi5324.group_project.Service;

import baylor.csi5324.group_project.Domain.Message;
import baylor.csi5324.group_project.Domain.User;
import baylor.csi5324.group_project.Exceptions.UserException;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageTests {

    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;

    @Test
    @Order(3)
    void createMessage() throws UserException {
        User user1 = new User();
        user1.setPassword("password");
        user1.setFirstName("Albert");
        user1.setLastName("Slater");
        user1.setStreet("123 Bayside Dr");
        user1.setCity("Los Angeles");
        user1.setState("CA");
        user1.setEmail("slater3@bayside.edu");
        user1.setPhone("123-456-7890");
        user1.setZip("90210");

        User savedUser1 = userService.newUser(user1);

        User user2 = new User();
        user2.setPassword("password");
        user2.setFirstName("Zack");
        user2.setLastName("Morris");
        user2.setStreet("123 Bayside Dr");
        user2.setCity("Los Angeles");
        user2.setState("CA");
        user2.setEmail("morris3@bayside.edu");
        user2.setPhone("123-456-7890");
        user2.setZip("90210");

        User savedUser2 = userService.newUser(user2);

        Message message = new Message();
        message.setMessage("Meet me at the Pizza Palace at 4pm");
        message.setRead(false);
        message.setSender(user1);
        message.setReceiver(user2);

        Message savedMessage1 = messageService.save(message);

        List<Message> unread1 = messageService.findAllUnreadMessages(user2.getId());

        assert (!unread1.isEmpty());
        assertEquals(savedMessage1, unread1.get(0));

        savedMessage1.setRead(true);
        savedMessage1.setReadTime(Timestamp.from(Instant.now()));
        Message savedMessage2 = messageService.save(savedMessage1);

        List<Message> unread2 = messageService.findAllUnreadMessages(user2.getId());
        assert (unread2.isEmpty());


    }
}

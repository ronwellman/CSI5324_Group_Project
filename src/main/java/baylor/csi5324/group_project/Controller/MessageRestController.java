package baylor.csi5324.group_project.Controller;

import baylor.csi5324.group_project.Domain.Message;
import baylor.csi5324.group_project.Domain.MessageDTO;
import baylor.csi5324.group_project.Exceptions.MessageException;
import baylor.csi5324.group_project.Exceptions.UserException;
import baylor.csi5324.group_project.Service.MessageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class MessageRestController {

    private final MessageService messageService;

    public MessageRestController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping(value = "/new_message")
    public ResponseEntity<Message> addMessage(@Valid @RequestBody MessageDTO dto) {
        try {
            return new ResponseEntity(messageService.addMessage(dto), HttpStatus.CREATED);
        } catch (UserException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/unread_messages")
    public ResponseEntity<List<Message>> getUnread(@RequestParam(value = "id") Long receiverId) {
        try {
            return new ResponseEntity(messageService.findAllUnreadMessages(receiverId), HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/sent_messages")
    public ResponseEntity<List<Message>> getSent(@RequestParam(value = "id") Long senderId) {
        try {
            return new ResponseEntity(messageService.findAllSentMessages(senderId), HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/received_messages")
    public ResponseEntity<List<Message>> getReceived(@RequestParam(value = "id") Long receiverId) {
        try {
            return new ResponseEntity(messageService.findAllReceivedMessages(receiverId), HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/read_message")
    public ResponseEntity<Message> readMessage(@RequestParam(value = "messageId") Long messageId, @RequestParam(value = "userId") Long userId) {
        try {
            return new ResponseEntity(messageService.readMessage(messageId, userId), HttpStatus.OK);
        } catch (MessageException | UserException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

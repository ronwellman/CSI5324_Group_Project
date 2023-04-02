package baylor.csi5324.group_project.Service.Impl;

import baylor.csi5324.group_project.Domain.Message;
import baylor.csi5324.group_project.Domain.MessageDTO;
import baylor.csi5324.group_project.Domain.User;
import baylor.csi5324.group_project.Exceptions.MessageException;
import baylor.csi5324.group_project.Exceptions.UserException;
import baylor.csi5324.group_project.Repository.MessageRepository;
import baylor.csi5324.group_project.Service.MessageService;
import baylor.csi5324.group_project.Service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserService userService;

    public MessageServiceImpl(MessageRepository messageRepository, UserService userService) {
        this.messageRepository = messageRepository;
        this.userService = userService;
    }

    @Override
    public Message save(Message message) {
        return messageRepository.saveAndFlush(message);
    }

    @Override
    @Transactional
    public Message addMessage(MessageDTO dto) throws UserException {
        Optional<User> tmpSender = userService.findById(dto.senderId);
        if (tmpSender.isEmpty()) {
            throw new UserException("invalid sender id");
        }
        Optional<User> tmpReceiver = userService.findById(dto.receiverId);
        if (tmpReceiver.isEmpty()) {
            throw new UserException("invalid receiver id");
        }

        User sender = tmpSender.get();
        User receiver = tmpReceiver.get();

        Message message = new Message();
        message.setMessage(dto.message);
        message.setRead(false);
        if (null != dto.subject) {
            message.setSubject(dto.subject);
        }

        message.setSender(sender);
        message.setReceiver(receiver);

        sender.addSentMessages(message);
        receiver.addReceivedMessages(message);

        Message saved = messageRepository.saveAndFlush(message);
        userService.save(sender);
        userService.save(receiver);

        return message;
    }

    @Override
    public List<Message> findAllUnreadMessages(Long receiverId) throws UserException {
        Optional<User> tmpReceiver = userService.findById(receiverId);
        if (tmpReceiver.isEmpty()) {
            throw new UserException("invalid receiver id");
        }

        return messageRepository.findByReceiverAndRead(tmpReceiver.get(), false);
    }

    @Override
    public List<Message> findAllSentMessages(Long senderId) throws UserException {
        Optional<User> tmpSender = userService.findById(senderId);
        if (tmpSender.isEmpty()) {
            throw new UserException("invalid sender id");
        }

        return messageRepository.findBySender(tmpSender.get());
    }

    @Override
    public List<Message> findAllReceivedMessages(Long receiverId) throws UserException {
        Optional<User> tmpReceiver = userService.findById(receiverId);
        if (tmpReceiver.isEmpty()) {
            throw new UserException("invalid receiver id");
        }

        return messageRepository.findByReceiver(tmpReceiver.get());
    }

    @Override
    public Optional<Message> findById(Long messageId) {
        return messageRepository.findById(messageId);
    }

    @Override
    public Message readMessage(Long messageId, Long userId) throws MessageException, UserException {
        Optional<Message> tmpMessage = messageRepository.findById(messageId);
        if (tmpMessage.isEmpty()) {
            throw new MessageException("invalid message id");
        }

        Optional<User> tmpUser = userService.findById(userId);
        if (tmpUser.isEmpty()) {
            throw new UserException("invalid userId id");
        }

        Message message = tmpMessage.get();
        User user = tmpUser.get();

        if (!message.getRead() && user.equals(message.getReceiver())) {
            message.setRead(true);
            message.setReadTime(Timestamp.from(Instant.now()));
            return messageRepository.saveAndFlush(message);
        }

        return message;
    }


}

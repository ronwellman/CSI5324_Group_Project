package baylor.csi5324.group_project.Service;

import baylor.csi5324.group_project.Domain.Message;
import baylor.csi5324.group_project.Domain.MessageDTO;
import baylor.csi5324.group_project.Exceptions.MessageException;
import baylor.csi5324.group_project.Exceptions.UserException;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    public Message save(Message message);

    public Message addMessage(MessageDTO dto) throws UserException;

    public List<Message> findAllUnreadMessages(Long receiverId) throws UserException;

    public List<Message> findAllSentMessages(Long senderId) throws UserException;

    public List<Message> findAllReceivedMessages(Long receiverId) throws UserException;

    public Optional<Message> findById(Long messageId);

    public Message readMessage(Long messageId, Long userId) throws MessageException, UserException;

}

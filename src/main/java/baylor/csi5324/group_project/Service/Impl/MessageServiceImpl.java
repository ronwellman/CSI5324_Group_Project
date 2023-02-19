package baylor.csi5324.group_project.Service.Impl;

import baylor.csi5324.group_project.Domain.Message;
import baylor.csi5324.group_project.Domain.User;
import baylor.csi5324.group_project.Repository.MessageRepository;
import baylor.csi5324.group_project.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public List<Message> findAllUnread(User user) {
        return messageRepository.findByReceiverAndRead(user, false);
    }
}

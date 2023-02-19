package baylor.csi5324.group_project.Service;

import baylor.csi5324.group_project.Domain.Message;
import baylor.csi5324.group_project.Domain.User;

import java.util.List;

public interface MessageService {
    public Message save(Message message);

    public List<Message> findAllUnread(User user);
}

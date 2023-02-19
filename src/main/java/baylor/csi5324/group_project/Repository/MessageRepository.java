package baylor.csi5324.group_project.Repository;

import baylor.csi5324.group_project.Domain.Message;
import baylor.csi5324.group_project.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    public List<Message> findBySender(User user);

    public List<Message> findByReceiver(User user);

    public List<Message> findByReceiverAndRead(User user, Boolean read);


}
package baylor.csi5324.group_project.Repository;

import baylor.csi5324.group_project.Domain.Message;
import baylor.csi5324.group_project.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    public List<Message> findBySender(User user);

    public List<Message> findByReceiver(User user);

    public List<Message> findByReceiverAndRead(User receiver, Boolean read);


}
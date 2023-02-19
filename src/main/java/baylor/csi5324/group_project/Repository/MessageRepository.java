package baylor.csi5324.group_project.Repository;

import baylor.csi5324.group_project.Domain.Message;
import baylor.csi5324.group_project.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
    public Iterable<Message> findBySender(User user);

    public Iterable<Message> findByReceiver(User user);

    public Iterable<Message> findByReceiverAndRead(User user, Boolean read);
}
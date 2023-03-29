package baylor.csi5324.group_project.Repository;

import baylor.csi5324.group_project.Domain.Bid;
import baylor.csi5324.group_project.Domain.Commission;
import baylor.csi5324.group_project.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Long> {
    public List<Bid> findBidsByCommission(Commission commission);

    public List<Bid> findBidsByUser(User user);
}

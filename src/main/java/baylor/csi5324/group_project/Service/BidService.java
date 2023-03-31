package baylor.csi5324.group_project.Service;

import baylor.csi5324.group_project.Domain.Bid;
import baylor.csi5324.group_project.Domain.BidDTO;
import baylor.csi5324.group_project.Exceptions.CommissionException;
import baylor.csi5324.group_project.Exceptions.UserException;

import java.util.List;
import java.util.Optional;

public interface BidService {
    public Bid addBid(BidDTO dto) throws UserException, CommissionException;

    public Optional<Bid> findById(Long id);

    public List<Bid> findBidsByCommissionId(Long id) throws CommissionException;

    public List<Bid> findBidsByUserId(Long id) throws UserException;

    public void deleteBid(Long id);
}

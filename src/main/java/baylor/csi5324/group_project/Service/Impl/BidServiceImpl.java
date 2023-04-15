package baylor.csi5324.group_project.Service.Impl;

import baylor.csi5324.group_project.Domain.Bid;
import baylor.csi5324.group_project.Domain.BidDTO;
import baylor.csi5324.group_project.Domain.Commission;
import baylor.csi5324.group_project.Domain.User;
import baylor.csi5324.group_project.Exceptions.CommissionException;
import baylor.csi5324.group_project.Exceptions.UserException;
import baylor.csi5324.group_project.Repository.BidRepository;
import baylor.csi5324.group_project.Service.BidService;
import baylor.csi5324.group_project.Service.CommissionService;
import baylor.csi5324.group_project.Service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidServiceImpl implements BidService {

    private final BidRepository bidRepository;
    private final UserService userService;
    private final CommissionService commissionService;

    public BidServiceImpl(BidRepository bidRepository, UserService userService, CommissionService commissionService) {
        this.bidRepository = bidRepository;
        this.userService = userService;
        this.commissionService = commissionService;
    }

    @Override
    @Transactional
    public Bid addBid(BidDTO dto) throws UserException, CommissionException {
        Optional<User> tmpUser = userService.findById(dto.userId);
        if (tmpUser.isEmpty()) {
            throw new UserException("invalid user id");
        }

        Optional<Commission> tmpCommission = commissionService.findById(dto.commissionId);
        if (tmpCommission.isEmpty()) {
            throw new CommissionException("invalid commission id");
        }

        User user = tmpUser.get();
        Commission commission = tmpCommission.get();

        if (user.equals(commission.getUser())) {
            throw new CommissionException("freelancer and consumer cannot be the same person");
        }

        Bid bid = new Bid();
        bid.setCompensationType(dto.compensationType);
        bid.setCompensationAmount(dto.compensationAmount);
        bid.setUser(user);
        bid.setCommission(commission);
        Bid saved = bidRepository.saveAndFlush(bid);

        user.addBid(saved);
        userService.save(user);

        commission.addBids(saved);
        commissionService.save(commission);

        return saved;
    }

    @Override
    public Optional<Bid> findById(Long id) {
        return bidRepository.findById(id);
    }

    @Override
    public List<Bid> findBidsByCommissionId(Long id) throws CommissionException {
        Optional<Commission> commission = commissionService.findById(id);
        if (commission.isEmpty()) {
            throw new CommissionException("invalid commission id");
        }
        return bidRepository.findBidsByCommission(commission.get());
    }

    @Override
    public List<Bid> findBidsByUserId(Long id) throws UserException {
        Optional<User> user = userService.findById(id);
        if (user.isEmpty()) {
            throw new UserException("invalid user id");
        }
        return bidRepository.findBidsByUser(user.get());
    }

    @Override
    public void deleteBid(Long id) {

        bidRepository.deleteById(id);
    }
}

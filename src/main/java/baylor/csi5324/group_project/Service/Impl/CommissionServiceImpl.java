package baylor.csi5324.group_project.Service.Impl;

import baylor.csi5324.group_project.Domain.Commission;
import baylor.csi5324.group_project.Domain.CommissionDTO;
import baylor.csi5324.group_project.Domain.User;
import baylor.csi5324.group_project.Repository.CommissionRepository;
import baylor.csi5324.group_project.Service.CommissionService;
import baylor.csi5324.group_project.Service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CommissionServiceImpl implements CommissionService {

    private final CommissionRepository commissionRepository;
    private final UserService userService;

    public CommissionServiceImpl(CommissionRepository commissionRepository, UserService userService) {
        this.commissionRepository = commissionRepository;
        this.userService = userService;
    }

    public void updateCommission(Commission commission) {
        String updatedListingTitle = commission.getListingTitle();
        String updatedListingDescription = commission.getDescription();
        LocalDate updatedListingDeadline = commission.getDeadline();
        Float updatedListingBudget = commission.getBudget();

        commission.setListingTitle(updatedListingTitle);
        commission.setDescription(updatedListingDescription);
        commission.setDeadline(updatedListingDeadline);
        commission.setBudget(updatedListingBudget);

    }

    @Override
    @Transactional
    public Commission addCommission(CommissionDTO dto) {
        Optional<User> tmpUser = userService.findById(dto.userId);
        if (tmpUser.isEmpty()) {
            return null;
        }

        User user = tmpUser.get();

        Commission commission = new Commission();
        commission.setUser(user);
        commission.setListingTitle(dto.listingTitle);
        commission.setDescription(dto.description);
        commission.setBudget(dto.budget);
        commission.setDeadline(dto.deadline);

        Commission saved = commissionRepository.save(commission);
        user.addCommission(saved);
        userService.save(user);

        return commissionRepository.saveAndFlush(commission);
    }

    @Override
    public Optional<Commission> getCommissionById(Long id) {
        return commissionRepository.findById(id);
    }

    @Override
    public List<Commission> getActiveCommissions() {
        return commissionRepository.findCommissionByActive(true);
    }

    @Override
    public List<Commission> getCommissionsByUser(Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isEmpty()) {
            return null;
        }

        return commissionRepository.findCommissionByUser(user.get());
    }

    @Override
    public void deleteCommission(Long id) {
        commissionRepository.deleteById(id);
    }
}

package baylor.csi5324.group_project.Service;

import java.time.LocalDate;

import baylor.csi5324.group_project.Domain.Commission;

public class CommissionService {

    public Commission addCommission(Long id, String listingTitle, String description, LocalDate deadline, float budget){
        Commission commission = new Commission(id, listingTitle, description, deadline, budget);
        return commission;
    }

    public void updateCommission(Commission commission) {
        String updatedListingTitle = commission.getListingTitle();
        String updatedListingDescription = commission.getDescription();
        LocalDate updatedListingDeadline = commission.getDeadline();
        float updatedListingBudget = commission.getBudget();

        commission.setListingTitle(updatedListingTitle);
        commission.setDescription(updatedListingDescription);
        commission.setDeadline(updatedListingDeadline);
        commission.setBudget(updatedListingBudget);

    }

}

package baylor.csi5324.group_project.Domain;

import java.time.LocalDate;

import baylor.csi5324.group_project.Repository.CommissionRepository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CommissionTests {

    @Autowired
    private CommissionRepository commissionRepository;

    @Test
    void createNewCommission() {

        Commission commission = new Commission();
        commission.setListingTitle("Commission Title");
        commission.setDescription("Commission description");
        commission.setDeadline(LocalDate.parse("2023-04-27"));
        commission.setBudget(46.87f);

        Commission savedCommission = commissionRepository.save(commission);

        Optional<Commission> commissionById = commissionRepository.findById(commission.getId());
        Optional<Commission> commissionByListingTitle = commissionRepository.findCommissionByListingTitle(commission.getListingTitle());

        assertTrue(commissionById.isPresent());
        assertEquals(savedCommission, commissionById.get());
        assertTrue(commissionByListingTitle.isPresent());
        assertEquals(savedCommission, commissionByListingTitle.get());
    }
}


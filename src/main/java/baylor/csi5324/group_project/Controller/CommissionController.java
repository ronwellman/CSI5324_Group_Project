package baylor.csi5324.group_project.Controller;
import baylor.csi5324.group_project.Domain.Commission;
import baylor.csi5324.group_project.Repository.CommissionRepository;
import baylor.csi5324.group_project.Service.CommissionService;

import java.time.LocalDate;
import java.util.Optional;

import jakarta.validation.*;
import org.springframework.validation.Errors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;



@Controller
@RequestMapping("/commissions")
@SessionAttributes("commissionRequest")
public class CommissionController {

    private CommissionRepository commissionRepository;
    private CommissionService commissionService;

    public CommissionController(CommissionRepository commissionRepository){
        this.commissionRepository = commissionRepository;
    }

    @GetMapping("/commission")
    public Optional<Commission> getCommission(Long id){
        return commissionRepository.findCommissionById(id);
    }

    @GetMapping("/request-service")
    public Commission addCommission(Long id, String listingTitle, String description, LocalDate deadline, float budget){
        return commissionService.addCommission(id, listingTitle, description, deadline, budget);
    }

    @PostMapping
    public String processRequest(
            @Valid Commission commission, Errors errors, SessionStatus sessionStatus) {
        if (errors.hasErrors()){
            return "commissionRequestForm";
        }

        commissionRepository.save(commission);
        sessionStatus.setComplete();

        return "redirect:/";
    }


}

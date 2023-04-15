package baylor.csi5324.group_project.Controller;

import baylor.csi5324.group_project.Domain.Contract;
import baylor.csi5324.group_project.Domain.DatesDTO;
import baylor.csi5324.group_project.Domain.Job;
import baylor.csi5324.group_project.Exceptions.*;
import baylor.csi5324.group_project.Service.JobService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.POST})
public class JobRestController {

    private final JobService jobService;

    public JobRestController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping(value = "/hire_freelancer")
    public ResponseEntity<Job> hireFreelancer(@RequestParam(value = "freelancePostId") Long freelancePostId, @RequestParam(value = "userId") Long userId) {
        try {
            return new ResponseEntity(jobService.hireFreelancer(freelancePostId, userId), HttpStatus.CREATED);
        } catch (FreelancePostException | UserException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/accept_bid")
    public ResponseEntity<Job> acceptBid(@RequestParam(value = "id") Long bidId) {
        try {
            return new ResponseEntity(jobService.acceptBid(bidId), HttpStatus.CREATED);
        } catch (BidException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/job")
    public ResponseEntity<Job> getJob(@RequestParam(value = "id") Long jobId) {
        Optional<Job> tmpJob = jobService.findJobById(jobId);
        if (tmpJob.isEmpty()) {
            return new ResponseEntity("invalid job id", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(tmpJob.get(), HttpStatus.OK);
    }

    @GetMapping(value = "/contract")
    public ResponseEntity<Contract> getContract(@RequestParam(value = "id") Long contractId) {
        Optional<Contract> tmpContract = jobService.findContractById(contractId);
        if (tmpContract.isEmpty()) {
            return new ResponseEntity("invalid contract id", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(tmpContract.get(), HttpStatus.OK);
    }

    @GetMapping(value = "/jobs_by_freelancer")
    public ResponseEntity<List<Job>> getJobsByFreelancer(@RequestParam(value = "id") Long freelancerId) {
        try {
            return new ResponseEntity(jobService.findJobsByFreelancer(freelancerId), HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/jobs_by_consumer")
    public ResponseEntity<List<Job>> getJobsByConsumer(@RequestParam(value = "id") Long consumerId) {
        try {
            return new ResponseEntity(jobService.findJobsByConsumer(consumerId), HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/cancel_job")
    public ResponseEntity<Job> cancelJob(@RequestParam(value = "id") Long jobId) {
        try {
            return new ResponseEntity(jobService.cancelJob(jobId), HttpStatus.OK);
        } catch (JobException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/complete_job")
    public ResponseEntity<Job> completeJob(@RequestParam(value = "id") Long jobId) {
        try {
            return new ResponseEntity(jobService.completeJob(jobId), HttpStatus.OK);
        } catch (JobException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/job_dates")
    public ResponseEntity<Job> setJobDates(@Valid @RequestBody DatesDTO dto) {
        try {
            return new ResponseEntity(jobService.adjustDates(dto), HttpStatus.OK);
        } catch (JobException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/sign_contract")
    public ResponseEntity<Contract> signContract(@RequestParam(value = "contractId") Long contractId, @RequestParam(value = "userId") Long userId) {
        try {
            return new ResponseEntity(jobService.signContract(contractId, userId), HttpStatus.OK);
        } catch (JobException | ContractException | UserException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

package baylor.csi5324.group_project.Service.Impl;

import baylor.csi5324.group_project.Domain.*;
import baylor.csi5324.group_project.Exceptions.BidException;
import baylor.csi5324.group_project.Exceptions.FreelancePostException;
import baylor.csi5324.group_project.Exceptions.JobException;
import baylor.csi5324.group_project.Exceptions.UserException;
import baylor.csi5324.group_project.Repository.ContractRepository;
import baylor.csi5324.group_project.Repository.JobRepository;
import baylor.csi5324.group_project.Service.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final ContractRepository contractRepository;
    private final UserService userService;
    private final FreelancePostService freelancePostService;
    private final CommissionService commissionService;
    private final BidService bidService;

    public JobServiceImpl(JobRepository jobRepository,
                          ContractRepository contractRepository,
                          UserService userService,
                          FreelancePostService freelancePostService,
                          CommissionService commissionService,
                          BidService bidService) {
        this.jobRepository = jobRepository;
        this.contractRepository = contractRepository;
        this.freelancePostService = freelancePostService;
        this.commissionService = commissionService;
        this.userService = userService;
        this.bidService = bidService;
    }

    @Override
    @Transactional
    public Job hireFreelancer(Long freelancerPostId, Long consumerId) throws FreelancePostException, UserException {
        Optional<FreelancePost> tmpPost = freelancePostService.findById(freelancerPostId);
        if (tmpPost.isEmpty()) {
            throw new FreelancePostException("invalid freelancePost id");
        }

        FreelancePost freelancePost = tmpPost.get();
        User freelancer = freelancePost.getUser();

        Optional<User> tmpConsumer = userService.findById(consumerId);
        if (tmpConsumer.isEmpty()) {
            throw new UserException("invalid user id");
        }

        User consumer = tmpConsumer.get();
        Job job = new Job();
        job.setFreelancePost(freelancePost);

        Contract contract = new Contract();
        contract.setJob(job);
        contract.setFreelancer(freelancer);
        contract.setConsumer(consumer);
        contract.setCompensationType(freelancePost.getCompensationType());
        contract.setCompensationAmount(freelancePost.getCompensationAmount());

        freelancer.addFreelanceContract(contract);
        consumer.addConsumerContract(contract);
        job.setContract(contract);

        freelancePost.addJob(job);

        job = jobRepository.saveAndFlush(job);
        contractRepository.saveAndFlush(contract);
        freelancePostService.save(freelancePost);
        userService.save(freelancer);
        userService.save(consumer);

        return job;
    }

    @Override
    @Transactional
    public Job acceptBid(Long bidId) throws BidException {
        Optional<Bid> tmpBid = bidService.findById(bidId);
        if (tmpBid.isEmpty()) {
            throw new BidException("invalid bid id");
        }

        Bid bid = tmpBid.get();
        User freelancer = bid.getUser();
        Commission commission = bid.getCommission();
        CompensationType compensationType = bid.getCompensationType();
        BigDecimal compensationAmount = bid.getCompensationAmount();
        User consumer = commission.getUser();

        Job job = new Job();
        job.setCommission(commission);

        commission.setJob(job);
        commission.setStatus(CommissionStatus.HIRED);

        Contract contract = new Contract();
        contract.setJob(job);
        contract.setFreelancer(freelancer);
        contract.setConsumer(consumer);
        contract.setCompensationType(compensationType);
        contract.setCompensationAmount(compensationAmount);

        freelancer.addFreelanceContract(contract);
        consumer.addConsumerContract(contract);
        job.setContract(contract);
        
        job = jobRepository.saveAndFlush(job);
        contractRepository.saveAndFlush(contract);
        commissionService.save(commission);
        userService.save(freelancer);
        userService.save(consumer);

        return job;
    }

    @Override
    public Job save(Job job) {
        return jobRepository.saveAndFlush(job);
    }

    @Override
    public Optional<Job> findJobById(Long jobId) {
        return jobRepository.findById(jobId);
    }

    @Override
    public Optional<Contract> findContractById(Long contractId) {
        return contractRepository.findById(contractId);
    }

    @Override
    public List<Job> findJobsByFreelancer(Long freelancerId) throws UserException {
        Optional<User> tmpFreelancer = userService.findById(freelancerId);
        if (tmpFreelancer.isEmpty()) {
            throw new UserException("invalid user id");
        }

        List<Job> jobs = new ArrayList<>();
        List<Contract> contracts = contractRepository.findAllByFreelancer(tmpFreelancer.get());

        for (Contract contract : contracts) {
            jobs.add(contract.getJob());
        }

        return jobs;
    }

    @Override
    public List<Job> findJobsByConsumer(Long consumerId) throws UserException {
        Optional<User> tmpConsumer = userService.findById(consumerId);
        if (tmpConsumer.isEmpty()) {
            throw new UserException("invalid user id");
        }

        List<Job> jobs = new ArrayList<>();
        List<Contract> contracts = contractRepository.findAllByConsumer(tmpConsumer.get());

        for (Contract contract : contracts) {
            jobs.add(contract.getJob());
        }

        return jobs;
    }

    @Override
    public Job cancelJob(Long jobId) throws JobException {
        Optional<Job> tmpJob = jobRepository.findById(jobId);
        if (tmpJob.isEmpty()) {
            throw new JobException("invalid job id");
        }

        Job job = tmpJob.get();
        job.setStatus(JobStatus.CANCELLED);
        return jobRepository.saveAndFlush(job);
    }
}

package baylor.csi5324.group_project.Service;

import baylor.csi5324.group_project.Domain.Contract;
import baylor.csi5324.group_project.Domain.DatesDTO;
import baylor.csi5324.group_project.Domain.Job;
import baylor.csi5324.group_project.Exceptions.*;

import java.util.List;
import java.util.Optional;

public interface JobService {
    public Job hireFreelancer(Long freelancerPostId, Long consumerId) throws FreelancePostException, UserException;

    public Job acceptBid(Long bidId) throws BidException;

    public Job save(Job job);

    public Optional<Job> findJobById(Long jobId);

    public Optional<Contract> findContractById(Long contractId);

    public List<Job> findJobsByFreelancer(Long freelancerId) throws UserException;

    public List<Job> findJobsByConsumer(Long consumerId) throws UserException;

    public Job cancelJob(Long jobId) throws JobException;

    public Job adjustDates(DatesDTO dto) throws JobException;

    public Contract signContract(Long contractId, Long userId) throws ContractException, UserException, JobException;

    public Job completeJob(Long jobId) throws JobException;
}

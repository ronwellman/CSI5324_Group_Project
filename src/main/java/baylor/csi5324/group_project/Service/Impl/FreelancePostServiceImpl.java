package baylor.csi5324.group_project.Service.Impl;

import baylor.csi5324.group_project.Domain.FreelancePost;
import baylor.csi5324.group_project.Domain.User;
import baylor.csi5324.group_project.Repository.FreelancePostRepository;
import baylor.csi5324.group_project.Service.FreelancePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FreelancePostServiceImpl implements FreelancePostService {

    @Autowired
    private FreelancePostRepository freelanceRepository;

    public FreelancePost save(FreelancePost freelancePost) {
        return freelanceRepository.save(freelancePost);
    }

    public List<FreelancePost> findAllByFreelancer(User user) {
        return freelanceRepository.findAllByFreelancer(user);
    }
}

package baylor.csi5324.group_project.Service.Impl;

import baylor.csi5324.group_project.Domain.FreelancePost;
import baylor.csi5324.group_project.Repository.FreelancePostRepository;
import baylor.csi5324.group_project.Service.FreelancePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FreelancePostServiceImpl implements FreelancePostService {

    @Autowired
    private FreelancePostRepository freelanceRepository;

    public FreelancePost newPost(FreelancePost freelancePost) {
        return freelanceRepository.save(freelancePost);
    }

    public FreelancePost updatePost(FreelancePost freelancePost) {
        return freelanceRepository.save(freelancePost);
    }
    
}

package baylor.csi5324.group_project.Service.Impl;

import baylor.csi5324.group_project.Domain.FreelancePost;
import baylor.csi5324.group_project.Domain.FreelancePostDTO;
import baylor.csi5324.group_project.Domain.User;
import baylor.csi5324.group_project.Repository.FreelancePostRepository;
import baylor.csi5324.group_project.Service.FreelancePostService;
import baylor.csi5324.group_project.Service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class FreelancePostServiceImpl implements FreelancePostService {

    private final FreelancePostRepository freelanceRepository;
    private final UserService userService;

    public FreelancePostServiceImpl(FreelancePostRepository freelanceRepository, @Lazy UserService userService) {
        this.freelanceRepository = freelanceRepository;
        this.userService = userService;
    }

    public FreelancePost addFreelancePost(FreelancePostDTO dto) {
        Optional<User> user = userService.findById(dto.userId);
        if (user.isEmpty()) {
            return null;
        }

        FreelancePost freelancePost = new FreelancePost();
        if (dto.active != null) {
            freelancePost.setActive(dto.active);
        }

        freelancePost.setCompensationAmount(dto.compensationAmount);
        freelancePost.setCompensationType(dto.compensationType);
        freelancePost.setDescription(dto.description);
        freelancePost.setListingTitle(dto.listingTitle);
        freelancePost.setFreelancer(user.get());
        freelancePost.setCreatedAt(Timestamp.from(Instant.now()));
        freelancePost.setLastUpdatedAT(freelancePost.getCreatedAt());

        FreelancePost saved = freelanceRepository.save(freelancePost);
        user.get().addFreelancePosts(saved);
        userService.save(user.get());

        return saved;
    }

    @Override
    public void deleteFreelancePost(Long id) {
        freelanceRepository.deleteById(id);
    }

    public List<FreelancePost> findAllByFreelancer(User user) {
        return freelanceRepository.findAllByFreelancer(user);
    }

    @Override
    public List<FreelancePost> findAllActiveFreelancePosts() {
        return freelanceRepository.findAllByActive(true);
    }

    @Override
    public FreelancePost disableFreelancePost(Long id) {
        Optional<FreelancePost> post = freelanceRepository.findById(id);
        if (post.isEmpty()) {
            return null;
        }

        FreelancePost freelancePost = post.get();
        freelancePost.setActive(false);

        return freelanceRepository.save(freelancePost);
    }

    @Override
    public FreelancePost enableFreelancePost(Long id) {
        Optional<FreelancePost> post = freelanceRepository.findById(id);
        if (post.isEmpty()) {
            return null;
        }

        FreelancePost freelancePost = post.get();
        freelancePost.setActive(true);

        return freelanceRepository.save(freelancePost);
    }
}

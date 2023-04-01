package baylor.csi5324.group_project.Service.Impl;

import baylor.csi5324.group_project.Domain.Job;
import baylor.csi5324.group_project.Domain.Review;
import baylor.csi5324.group_project.Domain.ReviewDTO;
import baylor.csi5324.group_project.Domain.User;
import baylor.csi5324.group_project.Exceptions.JobException;
import baylor.csi5324.group_project.Exceptions.UserException;
import baylor.csi5324.group_project.Repository.ReviewRepository;
import baylor.csi5324.group_project.Service.JobService;
import baylor.csi5324.group_project.Service.ReviewService;
import baylor.csi5324.group_project.Service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final JobService jobService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, @Lazy UserService userService, @Lazy JobService jobService) {
        this.reviewRepository = reviewRepository;
        this.userService = userService;
        this.jobService = jobService;
    }

    @Override
    @Transactional
    public Review addReview(ReviewDTO dto) throws JobException, UserException {
        Optional<User> tmpUser = userService.findById(dto.userId);
        if (tmpUser.isEmpty()) {
            throw new UserException("invalid user id");
        }

        Optional<Job> tmpJob = jobService.findJobById(dto.jobId);
        if (tmpJob.isEmpty()) {
            throw new JobException("invalid job id");
        }

        User user = tmpUser.get();
        Job job = tmpJob.get();

        Review review = new Review();
        review.setRating(dto.rating);
        review.setDescription(dto.description);
        review.setReviewer(user);
        review.setJob(job);

        user.addReview(review);
        job.setReview(review);

        Review savedReview = reviewRepository.saveAndFlush(review);
        userService.save(user);
        jobService.save(job);

        return savedReview;
    }

    @Override
    public Optional<Review> findById(Long id) {
        return reviewRepository.findById(id);
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.saveAndFlush(review);
    }

    @Override
    public List<Review> getReviewsLeftByUser(Long userId) throws UserException {
        Optional<User> tmpUser = userService.findById(userId);
        if (tmpUser.isEmpty()) {
            throw new UserException("invalid user id");
        }

        return reviewRepository.findAllByReviewer(tmpUser.get());
    }

    @Override
    public List<Review> getReviewsOfUser(Long rateeId) throws UserException {
        List<Job> jobs = jobService.findJobsByFreelancer(rateeId);
        List<Review> reviews = new ArrayList<>();
        for (Job job : jobs) {
            Review review = job.getReview();

            if (null != review) {
                reviews.add(review);
            }
        }

        return reviews;
    }

    @Override
    public Review getReviewByJob(Long jobId) throws JobException {
        Optional<Job> tmpJob = jobService.findJobById(jobId);
        if (tmpJob.isEmpty()) {
            throw new JobException("invalid job id");
        }

        return tmpJob.get().getReview();
    }

    @Override
    public BigDecimal getAverageRatingByRatee(Long rateeId) throws UserException {
        List<Job> jobs = jobService.findJobsByFreelancer(rateeId);
        BigDecimal avg = new BigDecimal("0.0");
        int reviews = 0;

        for (Job job : jobs) {
            Review review = job.getReview();

            if (null != review) {
                reviews = reviews + 1;
                avg = avg.add(review.getRating());
            }
        }

        avg = avg.divide(new BigDecimal(reviews));

        return avg;
    }
}

package baylor.csi5324.group_project.Service;

import baylor.csi5324.group_project.Domain.Review;
import baylor.csi5324.group_project.Domain.ReviewDTO;
import baylor.csi5324.group_project.Exceptions.JobException;
import baylor.csi5324.group_project.Exceptions.UserException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ReviewService {

    public Review addReview(ReviewDTO dto) throws JobException, UserException;

    public Optional<Review> findById(Long id);

    public Review save(Review review);

    public List<Review> getReviewsLeftByUser(Long raterId) throws UserException;

    public List<Review> getReviewsOfUser(Long rateeId) throws UserException;

    public Review getReviewByJob(Long jobId) throws JobException;

    public BigDecimal getAverageRatingByRatee(Long rateeId) throws UserException;
}

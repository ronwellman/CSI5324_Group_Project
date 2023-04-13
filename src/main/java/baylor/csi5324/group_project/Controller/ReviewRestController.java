package baylor.csi5324.group_project.Controller;

import baylor.csi5324.group_project.Domain.Review;
import baylor.csi5324.group_project.Domain.ReviewDTO;
import baylor.csi5324.group_project.Exceptions.JobException;
import baylor.csi5324.group_project.Exceptions.UserException;
import baylor.csi5324.group_project.Service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.POST}, allowCredentials = "true")
public class ReviewRestController {

    private final ReviewService reviewService;

    public ReviewRestController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping(value = "/new_review")
    public ResponseEntity<Review> addReview(@Valid @RequestBody ReviewDTO dto) {
        try {
            return new ResponseEntity(reviewService.addReview(dto), HttpStatus.CREATED);
        } catch (UserException | JobException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/review")
    public ResponseEntity<Review> getReview(@RequestParam(value = "id") Long id) {
        return new ResponseEntity(reviewService.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/reviews_by_user")
    public ResponseEntity<List<Review>> getReviewsByReviewer(@RequestParam(value = "id") Long reviewerId) {
        try {
            return new ResponseEntity(reviewService.getReviewsLeftByUser(reviewerId), HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/reviews_of_user")
    public ResponseEntity<List<Review>> getReviewsOfUser(@RequestParam(value = "id") Long revieweeId) {
        try {
            return new ResponseEntity(reviewService.getReviewsOfUser(revieweeId), HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/review_by_job")
    public ResponseEntity<Review> getReviewByJob(@RequestParam(value = "id") Long jobId) {
        try {
            return new ResponseEntity(reviewService.getReviewByJob(jobId), HttpStatus.OK);
        } catch (JobException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/rating")
    public ResponseEntity<BigDecimal> getAverageRating(@RequestParam(value = "id") Long rateeId) {
        try {
            return new ResponseEntity(reviewService.getAverageRatingByRatee(rateeId), HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

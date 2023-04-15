package baylor.csi5324.group_project.Controller;

import baylor.csi5324.group_project.Domain.Bid;
import baylor.csi5324.group_project.Domain.BidDTO;
import baylor.csi5324.group_project.Exceptions.CommissionException;
import baylor.csi5324.group_project.Exceptions.UserException;
import baylor.csi5324.group_project.Service.BidService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.POST})
public class BidRestController {

    private final BidService bidService;

    public BidRestController(BidService bidService) {
        this.bidService = bidService;
    }

    @PostMapping(value = "/new_bid")
    public ResponseEntity<Bid> addBid(@Valid @RequestBody BidDTO dto) {
        try {
            return new ResponseEntity(bidService.addBid(dto), HttpStatus.CREATED);
        } catch (UserException | CommissionException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/bid")
    public ResponseEntity<Bid> getBidById(@RequestParam(value = "id") Long id) {
        return new ResponseEntity(bidService.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/bids_by_commission")
    public ResponseEntity<List<Bid>> getBidsByCommissionId(@RequestParam(value = "id") Long id) {
        try {
            return new ResponseEntity(bidService.findBidsByCommissionId(id), HttpStatus.OK);
        } catch (CommissionException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/bids_by_user")
    public ResponseEntity<List<Bid>> getBidsByUserId(@RequestParam(value = "id") Long id) {
        try {
            return new ResponseEntity(bidService.findBidsByUserId(id), HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/delete_bid")
    public ResponseEntity<Null> delete(@RequestParam(value = "id") Long id) {
        bidService.deleteBid(id);
        return new ResponseEntity(null, HttpStatus.OK);
    }

}

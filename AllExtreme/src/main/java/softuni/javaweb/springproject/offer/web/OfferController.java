package softuni.javaweb.springproject.offer.web;

import org.hibernate.UnsupportedLockAttemptException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import softuni.javaweb.springproject.offer.model.view.AllOfferViewModel;
import softuni.javaweb.springproject.offer.service.OfferService;
import softuni.javaweb.springproject.user.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/{sport}/market")
public class OfferController {

    private final OfferService offerService;
    private final UserService userService;

    @Autowired
    public OfferController(OfferService offerService, UserService userService) {
        this.offerService = offerService;
        this.userService = userService;
    }

    @GetMapping("")
    public String market(@PathVariable("sport")String sport, Model model){

        List<AllOfferViewModel> offers = this.offerService.getAllBySport(sport);

        model.addAttribute("offers",offers);


        return "market/market";
    }

//    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public String viewOffer(@PathVariable("id") String offerId, Model model,
                            Principal principal){

        model.addAttribute("offer",this.offerService.getById(offerId));
        if(principal != null) {
            model.addAttribute("allReadyAdded",
                    this.userService.checkIfExistInWishList(principal.getName(),offerId));
        }

        return "market/offer-details";
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{id}/addToWishList")
    public String addTOWishList(@PathVariable("id")String offerId, Principal principal,
                                @PathVariable("sport")String sport){
        this.userService.addToWishList(offerId,principal.getName());
        return "redirect:";
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}/delete")
    public String deleteOffer(@PathVariable("id")String id,
                              @PathVariable("sport")String sport,
                              Principal principal) {

            this.offerService.deleteOffer(id);


        return "redirect:/" + sport + "/market";
    }
}

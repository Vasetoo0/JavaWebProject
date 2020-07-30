package softuni.javaweb.springproject.offer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import softuni.javaweb.springproject.offer.service.OfferService;
import softuni.javaweb.springproject.user.service.UserService;

import java.security.Principal;

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

        model.addAttribute("offers",this.offerService.getAllBySport(sport));


        return "market/market";
    }

    @GetMapping("/{id}")
    public String viewOffer(@PathVariable("id") String offerId, Model model,
                            Principal principal){

        model.addAttribute("offer",this.offerService.getById(offerId));
        model.addAttribute("allReadyAdded",
                this.userService.checkIfExistInWishList(principal.getName(),offerId));
        
        return "market/offer-details";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{id}/addToWishList")
    public String addTOWishList(@PathVariable("id")String offerId, Principal principal,
                                @PathVariable("sport")String sport){
        this.userService.addToWishList(offerId,principal.getName());
        return "redirect:";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}/delete")
    public String deleteOffer(@PathVariable("id")String id,
                              @PathVariable("sport")String sport) {

        this.offerService.deleteOffer(id);

        return "redirect:/" + sport + "/market";
    }
}

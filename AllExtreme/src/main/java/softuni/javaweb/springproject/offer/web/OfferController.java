package softuni.javaweb.springproject.offer.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/{sport}/market")
public class OfferController {

    @GetMapping("")
    public String market(){

        return "market/market";
    }

    @GetMapping("/offer/{id}")
    public String viewOffer(){

        return "market/offer-details";
    }
}

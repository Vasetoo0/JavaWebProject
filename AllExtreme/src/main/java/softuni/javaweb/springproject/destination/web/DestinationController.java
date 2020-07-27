package softuni.javaweb.springproject.destination.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import softuni.javaweb.springproject.destination.service.DestinationService;

@Controller
@RequestMapping("/{sport}/destinations")
public class DestinationController {

    private final DestinationService destinationService;

    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }


    @GetMapping("")
    public String getDestinations(@PathVariable("sport") String sport, Model model){

        model.addAttribute("destinations", this.destinationService.getAllBySport(sport));

        return "destinations/destinations";
    }
}

package softuni.javaweb.springproject.findStore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import softuni.javaweb.springproject.findStore.service.FindStoreService;

@Controller
@RequestMapping("/{sport}/findStore")
public class FindStoreController {

    private final FindStoreService findStoreService;

    @Autowired
    public FindStoreController(FindStoreService findStoreService) {
        this.findStoreService = findStoreService;
    }

    @GetMapping("")
    public String findStore(@PathVariable("sport")String sport, Model model){

        model.addAttribute("stores", this.findStoreService.getStoresBySport(sport));

        return "stores/find-store";
    }
}

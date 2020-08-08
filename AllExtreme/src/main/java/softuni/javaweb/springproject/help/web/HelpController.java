package softuni.javaweb.springproject.help.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.javaweb.springproject.help.model.binding.RequestAddBindingModel;
import softuni.javaweb.springproject.help.service.RequestService;

import javax.validation.Valid;

@Controller
@RequestMapping("/help")
public class HelpController {

    private final RequestService requestService;

    @Autowired
    public HelpController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/FAQ")
    public String faq(){
        return "help/FAQ";
    }

    @GetMapping("/team")
    public String team(){
        return "help/about-us";
    }

    @GetMapping("/contact")
    public String contact(Model model){

        if(!model.containsAttribute("requestAddBindingModel")) {
            model.addAttribute("requestAddBindingModel", new RequestAddBindingModel());
        }

        return "help/contact";
    }


    @PostMapping("/request")
    public String request(@Valid @ModelAttribute("requestAddBindingModel")RequestAddBindingModel requestAddBindingModel,
                          BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("requestAddBindingModel", requestAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.requestAddBindingModel",
                    bindingResult);

        } else {

            this.requestService.addRequest(requestAddBindingModel);

            redirectAttributes.addFlashAttribute("requestSend", true);

        }
        return "redirect:contact";

    }

}

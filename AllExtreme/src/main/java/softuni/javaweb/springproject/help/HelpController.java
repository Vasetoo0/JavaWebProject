package softuni.javaweb.springproject.help;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/help")
public class HelpController {

    @GetMapping("/FAQ")
    public String faq(){
        return "help/FAQ";
    }

    @GetMapping("/team")
    public String team(){
        return "help/about-us";
    }

    @GetMapping("/contact")
    public String contact(){
        return "help/contact";
    }

    //TODO: Add POST method for contact :)

}

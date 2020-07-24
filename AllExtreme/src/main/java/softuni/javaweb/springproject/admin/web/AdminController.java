package softuni.javaweb.springproject.admin.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/addStory")
    public String addStory(){
        return "admin/add-story";
    }

//    @PostMapping("/addStory")
//    public String addStoryConfirm(@Valid ) {
//
//
//        return "redirect:/";
//    }

}

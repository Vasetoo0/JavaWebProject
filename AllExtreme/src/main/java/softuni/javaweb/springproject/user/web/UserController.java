package softuni.javaweb.springproject.user.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.javaweb.springproject.user.model.binding.UserRegisterBindingModel;
import softuni.javaweb.springproject.user.model.service.UserServiceModel;
import softuni.javaweb.springproject.user.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String login() {

        return "user/login";
    }

    @PostMapping("/login-error")
    public ModelAndView onLoginError(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.
                    SPRING_SECURITY_FORM_USERNAME_KEY) String email
    ) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error", "bad.credentials");
        modelAndView.addObject("username", email);
        modelAndView.setViewName("user/login");

        return modelAndView;
    }

    @GetMapping("/register")
    public String register(Model model) {

        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }

        return "user/register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid @ModelAttribute("userRegisterBindingModel")
                                          UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {
        if (this.userService.existsUser(userRegisterBindingModel.getUsername())) {
            bindingResult.rejectValue("username",
                    "error.username",
                    "An account with this username already exists.");
        }
        if (this.userService.existsEmail(userRegisterBindingModel.getEmail())) {
            bindingResult.rejectValue("email",
                    "error.email",
                    "An account with this email already exists.");
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "userRegisterBindingModel",
                    bindingResult);

            return "redirect:register";
        } else {
            this.userService.registerUser(
                    this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class)
            );
            redirectAttributes.addFlashAttribute("successRegister", true);

            return "redirect:login";
        }
    }
}
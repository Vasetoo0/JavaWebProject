package softuni.javaweb.springproject.user.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.javaweb.springproject.offer.model.binding.OfferAddBindingModel;
import softuni.javaweb.springproject.offer.service.OfferService;
import softuni.javaweb.springproject.user.model.binding.UserRegisterBindingModel;
import softuni.javaweb.springproject.user.model.service.UserServiceModel;
import softuni.javaweb.springproject.user.service.UserService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final OfferService offerService;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper, OfferService offerService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.offerService = offerService;
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

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{name}")
    public String profile(@PathVariable("name") String name, Model model) {


        model.addAttribute("user", this.userService.getByUsername(name));

        return "user/profile";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{name}/myOffers")
    public String myOffers(@PathVariable("name") String name, Model model) {

        if(!this.userService.existsUser(name)) {
            throw new EntityNotFoundException("User not found!");
        }

        model.addAttribute("myOffers", this.offerService.getByCreator(name));

        return "user/my-offers";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{name}/addOffer")
    public String addOffer(@PathVariable("name")String name,Model model) {

        if(!this.userService.existsUser(name)) {
            throw new EntityNotFoundException("User not found!");
        }

        if(!model.containsAttribute("offerAddBindingModel")) {
            model.addAttribute("offerAddBindingModel", new OfferAddBindingModel());
        }

        return "user/add-offer";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{name}/addOffer")
    public String addOfferConfirm(@Valid @ModelAttribute("offerAddBindingModel")OfferAddBindingModel offerAddBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                  @RequestParam("picture")String[] pictures, @PathVariable("name") String name,
                                  Principal principal){



        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerAddBindingModel", offerAddBindingModel);
            redirectAttributes.addFlashAttribute(redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerAddBindingModel",
                    bindingResult));

            return "redirect:addOffer";
        } else {
            offerAddBindingModel.setPictures(List.of(pictures));
            offerAddBindingModel.setCreator(principal.getName());

            this.offerService.addOffer(offerAddBindingModel);

            return "redirect:myOffers";
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{name}/wishList")
    public String getWishList(@PathVariable("name")String username,Model model){

        model.addAttribute("wishList", this.userService.getWishList(username));

        return "user/wish-list";
    }


}
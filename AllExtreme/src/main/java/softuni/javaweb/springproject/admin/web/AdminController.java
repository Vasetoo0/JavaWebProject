package softuni.javaweb.springproject.admin.web;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.javaweb.springproject.destination.model.binding.DestinationAddBindingModel;
import softuni.javaweb.springproject.destination.service.DestinationService;
import softuni.javaweb.springproject.event.model.binding.EventAddBindingModel;
import softuni.javaweb.springproject.event.service.EventService;
import softuni.javaweb.springproject.findStore.model.binding.StoreAddBindingModel;
import softuni.javaweb.springproject.findStore.service.FindStoreService;
import softuni.javaweb.springproject.help.service.RequestService;
import softuni.javaweb.springproject.offer.service.OfferService;
import softuni.javaweb.springproject.story.model.binding.StoryAddBindingModel;
import softuni.javaweb.springproject.story.service.StoryService;
import softuni.javaweb.springproject.video.model.binding.VideoAddBindingModel;
import softuni.javaweb.springproject.video.service.VideoService;

import javax.validation.Valid;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final StoryService storyService;
    private final RequestService requestService;
    private final VideoService videoService;
    private final EventService eventService;
    private final OfferService offerService;
    private final FindStoreService findStoreService;
    private final DestinationService destinationService;
    private final ModelMapper modelMapper;

    public AdminController(StoryService storyService, RequestService requestService, VideoService videoService, EventService eventService, OfferService offerService, FindStoreService findStoreService, DestinationService destinationService, ModelMapper modelMapper) {
        this.storyService = storyService;
        this.requestService = requestService;
        this.videoService = videoService;
        this.eventService = eventService;
        this.offerService = offerService;
        this.findStoreService = findStoreService;
        this.destinationService = destinationService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/addStory")
    public String addStory(Model model) {


        if (!model.containsAttribute("storyAddBindingModel")) {
            model.addAttribute("storyAddBindingModel", new StoryAddBindingModel());
        }

        return "admin/add-story";
    }

    @PostMapping("/addStory")
    public String addStoryConfirm(@Valid @ModelAttribute("storyAddBindingModel") StoryAddBindingModel storyAddBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("storyAddBindingModel", storyAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.storyAddBindingModel",
                    bindingResult);

            return "redirect:addStory";
        } else {
            this.storyService.addStory(storyAddBindingModel);

            return "redirect:/";
        }
    }

    @GetMapping("/addVideo")
    public String addVideo(Model model) {


        if (!model.containsAttribute("videoAddBindingModel")) {
            model.addAttribute("videoAddBindingModel", new VideoAddBindingModel());
        }

        return "admin/add-video";
    }

    @PostMapping("/addVideo")
    public String addVideoConfirm(@Valid @ModelAttribute("videoAddBindingModel") VideoAddBindingModel videoAddBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("videoAddBindingModel", videoAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.videoAddBindingModel",
                    bindingResult);

            return "redirect:addVideo";
        } else {
            this.videoService.addVideo(videoAddBindingModel);

            return "redirect:/";
        }
    }

    @GetMapping("/addDestination")
    public String addDestination(Model model) {


        if (!model.containsAttribute("destinationAddBindingModel")) {
            model.addAttribute("destinationAddBindingModel", new DestinationAddBindingModel());
        }

        return "admin/add-destination";
    }

    @PostMapping("/addDestination")
    public String addDestinationConfirm(@Valid @ModelAttribute("destinationAddBindingModel") DestinationAddBindingModel destinationAddBindingModel,
                                        BindingResult bindingResult, RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("destinationAddBindingModel", destinationAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.destinationAddBindingModel",
                    bindingResult);

            return "redirect:addDestination";
        } else {
            this.destinationService.addDestination(destinationAddBindingModel);

            return "redirect:/";
        }
    }

    @GetMapping("/addEvent")
    public String addEvent(Model model) {


        if (!model.containsAttribute("eventAddBindingModel")) {
            model.addAttribute("eventAddBindingModel", new EventAddBindingModel());
        }

        return "admin/add-event";
    }

    @PostMapping("/addEvent")
    public String addEventConfirm(@Valid @ModelAttribute("eventAddBindingModel") EventAddBindingModel eventAddBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("eventAddBindingModel", eventAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.eventAddBindingModel",
                    bindingResult);

            return "redirect:addEvent";
        } else {
            this.eventService.addEvent(eventAddBindingModel);

            return "redirect:/";
        }
    }

    @GetMapping("/approve")
    public String approve(Model model) {

        model.addAttribute("unApproved", this.offerService.getUnApproved());

        return "admin/approve-new-offer";
    }

    //TODO: Fix approve from get to post method!
    @GetMapping("/approve/{id}")
    public String approveConfirm(@PathVariable("id") String id) {

        this.offerService.approveOffer(id);

        return "redirect:/admin/approve";
    }

    @GetMapping("/requests")
    public String getRequests(Model model) {

        model.addAttribute("userRequests", this.requestService.getRequests());

        return "admin/requests";
    }

    @DeleteMapping("/requests/delete/{id}")
    public String deleteRequest(@PathVariable("id") String requestId) {

        this.requestService.deleteRequest(requestId);

        return "redirect:/admin/requests";
    }

    @GetMapping("/addStore")
    public String addStore(Model model) {

        if (!model.containsAttribute("storeAddBindingModel")) {
            model.addAttribute("storeAddBindingModel", new StoreAddBindingModel());
        }

        return "admin/add-store";
    }

    @PostMapping("/addStore")
    public String addStoreConfirm(@Valid @ModelAttribute("storeAddBindingModel") StoreAddBindingModel storeAddBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("storeAddBindingModel", storeAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.storeAddBindingModel",
                    bindingResult);

            return "redirect:addStore";
        } else {

            this.findStoreService.addStore(storeAddBindingModel);


            return "redirect:/";
        }
    }
}

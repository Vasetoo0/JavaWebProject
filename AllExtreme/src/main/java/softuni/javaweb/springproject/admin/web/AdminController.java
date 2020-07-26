package softuni.javaweb.springproject.admin.web;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    private final VideoService videoService;
    private final ModelMapper modelMapper;

    public AdminController(StoryService storyService, VideoService videoService, ModelMapper modelMapper) {
        this.storyService = storyService;
        this.videoService = videoService;
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


        if(!model.containsAttribute("videoAddBindingModel")) {
            model.addAttribute("videoAddBindingModel", new VideoAddBindingModel());
        }

        return "admin/add-video";
    }

    @PostMapping("/addVideo")
    public String addVideoConfirm(@Valid @ModelAttribute("videoAddBindingModel") VideoAddBindingModel videoAddBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes
                                  ){
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
}

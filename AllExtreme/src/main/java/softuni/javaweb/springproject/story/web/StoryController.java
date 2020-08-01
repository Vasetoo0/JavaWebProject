package softuni.javaweb.springproject.story.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import softuni.javaweb.springproject.story.service.StoryService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/{sport}/stories")
public class StoryController {

    private final StoryService storyService;

    @Autowired
    public StoryController(StoryService storyService) {
        this.storyService = storyService;
    }

    @GetMapping("")
    public String viewAllStories(@PathVariable("sport") String selectedSport, HttpSession httpSession,
                                 Model model) {

        httpSession.setAttribute("selectedSport", selectedSport);
        model.addAttribute("stories", this.storyService.getAllBySport(selectedSport));

        return "stories/stories";
    }

    @GetMapping("/read/{id}")
    public String viewStory(@PathVariable("id") String id,Model model,
                            @PathVariable("sport") String sport){

        model.addAttribute("story", this.storyService.getById(id));
        model.addAttribute("recent", this.storyService.getRecentStories(sport));

        return "stories/story-details";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteStory(@PathVariable("id")String id,
                              @PathVariable("sport") String sport) {
        this.storyService.deleteById(id);

        return "redirect:/" + sport + "/stories/";
    }




}

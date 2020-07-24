package softuni.javaweb.springproject.story.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/{sport}/stories")
public class StoryController {

    @GetMapping("")
    public String viewAllStories(@PathVariable("sport") String selectedSport) {

        return "stories/stories";
    }

}

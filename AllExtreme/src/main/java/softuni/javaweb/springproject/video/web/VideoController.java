package softuni.javaweb.springproject.video.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import softuni.javaweb.springproject.video.service.VideoService;

@Controller
@RequestMapping("/{sport}/videos")
public class VideoController {

    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("")
    public String getVideos(@PathVariable("sport") String sport, Model model) {

        model.addAttribute("videos", this.videoService.getBySport(sport));

        return "videos/videos";
    }

    //TODO: Test!
    @GetMapping("/delete/{id}")
    public String deleteVideo(@PathVariable("id")String id,
                              @PathVariable("sport") String sport) {
        this.videoService.deleteById(id);

        return "redirect:/" + sport + "/videos/";
    }



}

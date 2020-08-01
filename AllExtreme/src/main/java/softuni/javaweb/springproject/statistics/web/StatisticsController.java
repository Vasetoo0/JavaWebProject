package softuni.javaweb.springproject.statistics.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import softuni.javaweb.springproject.destination.service.DestinationService;
import softuni.javaweb.springproject.event.service.EventService;
import softuni.javaweb.springproject.findStore.service.FindStoreService;
import softuni.javaweb.springproject.offer.service.OfferService;
import softuni.javaweb.springproject.statistics.service.StatsService;
import softuni.javaweb.springproject.story.service.StoryService;
import softuni.javaweb.springproject.user.service.UserService;
import softuni.javaweb.springproject.video.service.VideoService;

import java.util.HashMap;

@PreAuthorize("hasRole('ADMIN')")
@Controller
@RequestMapping("/admin/stats")
public class StatisticsController {

    private StatsService statsService;

    private final UserService userService;
    private final EventService eventService;
    private final StoryService storyService;
    private final DestinationService destinationService;
    private final OfferService offerService;
    private final VideoService videoService;
    private final FindStoreService findStoreService;

    @Autowired
    public StatisticsController(StatsService statsService, UserService userService, EventService eventService, StoryService storyService, DestinationService destinationService,
                                OfferService offerService, VideoService videoService, FindStoreService findStoreService) {
        this.statsService = statsService;
        this.userService = userService;
        this.eventService = eventService;
        this.storyService = storyService;
        this.destinationService = destinationService;
        this.offerService = offerService;
        this.videoService = videoService;
        this.findStoreService = findStoreService;
    }

    @GetMapping("/charts")
    public String getStats(Model model){

        HashMap<String,Long> entitiesCount = new HashMap<>();

        entitiesCount.put("users",this.userService.getUsersCount());
        entitiesCount.put("stories",this.storyService.getStoriesCount());
        entitiesCount.put("destinations",this.destinationService.getDestinationsCount());
        entitiesCount.put("events",this.eventService.getEventsCount());
        entitiesCount.put("videos",this.videoService.getVideosCount());
        entitiesCount.put("offers",this.offerService.getOffersCount());
        entitiesCount.put("stores",this.findStoreService.getStoresCount());

        model.addAttribute("entitiesCount",entitiesCount);

        return "statistics/statistics";
    }

    @GetMapping("/addInfo")
    public String addInfo(Model model){

        model.addAttribute("newOffersAttempt", this.statsService.getOfferAddAttemptCount());
        model.addAttribute("storiesReadCount", this.statsService.getStoryReadAttemptCount());

        model.addAttribute("offerAddStartedOn", this.statsService.offerAddStartedOn());
        model.addAttribute("storyReadStartedOn", this.statsService.storyReadStartedOn());


        return "statistics/add-info";
    }
}

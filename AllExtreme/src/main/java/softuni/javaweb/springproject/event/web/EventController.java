package softuni.javaweb.springproject.event.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import softuni.javaweb.springproject.event.service.EventService;

@Controller
@RequestMapping("/{sport}/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("")
    public String getEvents(@PathVariable("sport") String sport, Model model){

        model.addAttribute("events", this.eventService.getAllBySport(sport));

        return "events/events";
    }

    @GetMapping("/details/{id}")
    public String getEventDetails(@PathVariable("id") String id,Model model,
                                  @PathVariable("sport") String sport){

        model.addAttribute("event", this.eventService.getById(id));
        model.addAttribute("randomEvents", this.eventService.getRandomEvents(sport));

        return "events/event-details";
    }

}

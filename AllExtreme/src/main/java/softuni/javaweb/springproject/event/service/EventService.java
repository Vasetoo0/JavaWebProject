package softuni.javaweb.springproject.event.service;

import softuni.javaweb.springproject.destination.model.binding.DestinationAddBindingModel;
import softuni.javaweb.springproject.event.model.binding.EventAddBindingModel;
import softuni.javaweb.springproject.event.model.service.EventServiceModel;
import softuni.javaweb.springproject.event.model.view.AllEventViewModel;
import softuni.javaweb.springproject.event.model.view.EventViewModel;

import java.util.List;

public interface EventService {
    EventServiceModel addEvent(EventAddBindingModel eventAddBindingModel);

    List<AllEventViewModel> getAllBySport(String sport);

    EventViewModel getById(String id);

    List<AllEventViewModel> getRandomEvents(String sport);

    Long getEventsCount();
}

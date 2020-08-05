package softuni.javaweb.springproject.event.service.impl;

import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.javaweb.springproject.destination.model.binding.DestinationAddBindingModel;
import softuni.javaweb.springproject.event.model.binding.EventAddBindingModel;
import softuni.javaweb.springproject.event.model.entity.Event;
import softuni.javaweb.springproject.event.model.service.EventServiceModel;
import softuni.javaweb.springproject.event.model.view.AllEventViewModel;
import softuni.javaweb.springproject.event.model.view.EventViewModel;
import softuni.javaweb.springproject.event.repository.EventRepository;
import softuni.javaweb.springproject.event.service.EventService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public EventServiceModel addEvent(EventAddBindingModel eventAddBindingModel) {

        EventServiceModel eventServiceModel = this.modelMapper.map(eventAddBindingModel, EventServiceModel.class);

        eventServiceModel.setPictures(eventServiceModel.getPictures()
                .stream()
                .filter(p -> !p.isEmpty() && !p.isBlank())
                .collect(Collectors.toList()));

        Event savedEvent = this.eventRepository.save(
                this.modelMapper.map(
                        eventServiceModel, Event.class
                ));

        return this.modelMapper.map(savedEvent,EventServiceModel.class);
    }

    @Override
    public List<AllEventViewModel> getAllBySport(String sport) {
        return this.eventRepository.findAll()
                .stream()
                .filter(e -> e.getSport().name().equals(sport))
                .map(e -> this.modelMapper.map(e,AllEventViewModel.class))
                .collect(Collectors.toList());
    }

    //TODO: Test throws error!
    @Override
    public EventViewModel getById(String id) {

        return this.eventRepository.findById(id)
                .map(e -> this.modelMapper.map(e,EventViewModel.class))
                .orElseThrow(() -> new EntityNotFoundException("By some reason event was not found!"));
    }

    @Override
    public List<AllEventViewModel> getRandomEvents(String sport) {
        Random random = new Random();

        List<Event> all = this.eventRepository.findAll();

        AllEventViewModel firstRandomEvent = this.modelMapper
                .map(all.get(random.nextInt(all.size())),AllEventViewModel.class);
        AllEventViewModel secondRandomEvent = this.modelMapper
                .map(all.get(random.nextInt(all.size())),AllEventViewModel.class);
        AllEventViewModel thirdRandomEvent = this.modelMapper
                .map(all.get(random.nextInt(all.size())),AllEventViewModel.class);

        return List.of(firstRandomEvent,secondRandomEvent,thirdRandomEvent);
    }

    @Override
    public Long getEventsCount() {
            return this.eventRepository.count();
    }
}

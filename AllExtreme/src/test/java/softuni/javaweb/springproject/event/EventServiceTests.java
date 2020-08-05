package softuni.javaweb.springproject.event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import softuni.javaweb.springproject.enums.Sport;
import softuni.javaweb.springproject.event.model.binding.EventAddBindingModel;
import softuni.javaweb.springproject.event.model.entity.Event;
import softuni.javaweb.springproject.event.model.service.EventServiceModel;
import softuni.javaweb.springproject.event.model.view.AllEventViewModel;
import softuni.javaweb.springproject.event.model.view.EventViewModel;
import softuni.javaweb.springproject.event.repository.EventRepository;
import softuni.javaweb.springproject.event.service.EventService;
import softuni.javaweb.springproject.event.service.impl.EventServiceImpl;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventServiceTests {

    private EventService serviceToTest;

    @Mock
    EventRepository mockEventRepository;

    @BeforeEach
    public void setUp(){
        serviceToTest = new EventServiceImpl(mockEventRepository,new ModelMapper());
    }

    @Test
    public void testGetEventsCount(){
        when(mockEventRepository.count()).thenReturn(10L);

        Assertions.assertEquals(10,serviceToTest.getEventsCount());
    }

    @Test
    public void testGEtRandomEvents() {
        Event event = new Event();
        event.setTitle("Test");
        Event event1 = new Event();
        event1.setTitle("Test1");
        Event event2 = new Event();
        event2.setTitle("Test2");
        Event event3 = new Event();
        event3.setTitle("Test3");
        Event event4 = new Event();
        event4.setTitle("Test4");

        when(mockEventRepository.findAll()).thenReturn(List.of(event,event1,event2,event3,event4));

        List<AllEventViewModel> returned = serviceToTest.getRandomEvents("CLIMBING");

        Assertions.assertEquals(3,returned.size());
    }

    @Test
    public void testGetById(){
        Event event = new Event();
        event.setTitle("Test");

        when(mockEventRepository.findById("123")).thenReturn(Optional.of(event));

        EventViewModel returned = serviceToTest.getById("123");

        Assertions.assertEquals(event.getTitle(),returned.getTitle());
        Assertions.assertSame(EventViewModel.class,returned.getClass());
    }

    @Test
    public void testGetAllBySport(){
        Event event = new Event();
        event.setTitle("Test");
        event.setSport(Sport.CLIMBING);
        Event event1 = new Event();
        event1.setTitle("Test1");
        event1.setSport(Sport.CLIMBING);
        Event event2 = new Event();
        event2.setTitle("Test2");
        event2.setSport(Sport.SNOWBOARDING);

        when(mockEventRepository.findAll()).thenReturn(List.of(event,event1,event2));

        List<AllEventViewModel> returned = serviceToTest.getAllBySport("CLIMBING");

        Assertions.assertEquals(2,returned.size());
        Assertions.assertEquals(event.getTitle(),returned.get(0).getTitle());
    }

    @Test
    public void testAddEvent() {
        EventAddBindingModel eventAddBindingModel = new EventAddBindingModel();
        eventAddBindingModel.setTitle("Test");
        eventAddBindingModel.setPictures(List.of("pic",""));

        Event savedEvent = new Event();
        savedEvent.setTitle("Test");

        when(mockEventRepository.save(any(Event.class))).thenReturn(savedEvent);

        EventServiceModel returnedFromSave = serviceToTest.addEvent(eventAddBindingModel);

        Assertions.assertEquals(savedEvent.getTitle(),returnedFromSave.getTitle());
        Assertions.assertSame(EventServiceModel.class,returnedFromSave.getClass());

    }


}

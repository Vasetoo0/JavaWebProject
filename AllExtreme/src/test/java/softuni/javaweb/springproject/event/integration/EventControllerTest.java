package softuni.javaweb.springproject.event.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import softuni.javaweb.springproject.event.model.view.AllEventViewModel;
import softuni.javaweb.springproject.event.model.view.EventViewModel;
import softuni.javaweb.springproject.event.service.EventService;
import softuni.javaweb.springproject.event.web.EventController;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    EventService eventService;

    @Test
    @WithMockUser
    public void testAllEventsView() throws Exception {

        mockMvc.perform(get("/CLIMBING/events"))
                .andExpect(model().attributeExists("events"))
                .andExpect(view().name("events/events"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void testEventView() throws Exception {
        EventViewModel eventViewModel = new EventViewModel();
        eventViewModel.setEventDate(LocalDateTime.now());
        eventViewModel.setPictures(List.of("pic"));

        AllEventViewModel allEventViewModel = new AllEventViewModel();
        allEventViewModel.setPictures(List.of("pic"));

        when(eventService.getById("123")).thenReturn(eventViewModel);
        when(eventService.getRandomEvents("CLIMBING")).thenReturn(List.of(allEventViewModel));

        mockMvc.perform(get("/CLIMBING/events/details/{id}","123"))
                .andExpect(model().attributeExists("event"))
                .andExpect(model().attributeExists("randomEvents"))
                .andExpect(view().name("events/event-details"))
                .andExpect(status().isOk());
    }


}

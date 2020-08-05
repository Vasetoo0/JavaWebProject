package softuni.javaweb.springproject.offer.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import softuni.javaweb.springproject.offer.service.OfferService;
import softuni.javaweb.springproject.story.web.StoryController;
import softuni.javaweb.springproject.user.service.UserService;


@SpringBootTest
@AutoConfigureMockMvc
public class OfferControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    OfferService offerService;

    @MockBean
    UserService userService;



}

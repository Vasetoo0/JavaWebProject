package softuni.javaweb.springproject.destination.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import softuni.javaweb.springproject.destination.service.DestinationService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DestinationControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DestinationService mockDestinationService;

    @Test
    @WithMockUser
    public void testDestinationsView() throws Exception {

        mockMvc.perform(get("/CLIMBING/destinations"))
                .andExpect(model().attributeExists("destinations"))
                .andExpect(view().name("destinations/destinations"))
                .andExpect(status().isOk());
    }
}

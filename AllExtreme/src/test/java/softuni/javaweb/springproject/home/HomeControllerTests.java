package softuni.javaweb.springproject.home;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import softuni.javaweb.springproject.offer.web.OfferController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = HomeController.class)
@AutoConfigureMockMvc
public class HomeControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser
    public void testHome() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(request().sessionAttributeDoesNotExist("selectedSport"))
                .andExpect(view().name("home"))
                .andExpect(status().isOk());
    }
}

package softuni.javaweb.springproject.offer.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import softuni.javaweb.springproject.enums.Sport;
import softuni.javaweb.springproject.offer.model.view.OfferViewModel;
import softuni.javaweb.springproject.offer.service.OfferService;
import softuni.javaweb.springproject.user.service.UserService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class OfferControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    OfferService offerService;

    @MockBean
    UserService userService;

    @Test
    @WithMockUser
    public void testMarket() throws Exception {

        mockMvc.perform(get("/CLIMBING/market"))
                .andExpect(status().isOk())
                .andExpect(view().name("market/market"))
                .andExpect(model().attributeExists("offers"));
    }

    @Test
    @WithMockUser
    public void testViewOffer() throws Exception {
        OfferViewModel offerViewModel = new OfferViewModel();
        offerViewModel.setCreator("Test");
        offerViewModel.setSport(Sport.CLIMBING);


        when(offerService.getById("1234")).thenReturn(offerViewModel);

        mockMvc.perform(get("/CLIMBING/market/{id}","1234"))
                .andExpect(view().name("market/offer-details"))
                .andExpect(model().attributeExists("offer"))
                .andExpect(model().attributeExists("allReadyAdded"))
                .andExpect(status().isOk());


    }

}

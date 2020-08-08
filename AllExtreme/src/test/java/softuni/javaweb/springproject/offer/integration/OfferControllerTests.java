package softuni.javaweb.springproject.offer.integration;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import softuni.javaweb.springproject.enums.Sport;
import softuni.javaweb.springproject.offer.model.view.OfferViewModel;
import softuni.javaweb.springproject.offer.service.OfferService;
import softuni.javaweb.springproject.user.service.UserService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

        mockMvc.perform(get("/CLIMBING/market/{id}", "1234"))
                .andExpect(view().name("market/offer-details"))
                .andExpect(model().attributeExists("offer"))
                .andExpect(model().attributeExists("allReadyAdded"))
                .andExpect(status().isOk());


    }

    @Test
    @WithMockUser(username = "Vas", roles = {"USER", "ADMIN"})
    public void testAddToWishList() throws Exception {

        mockMvc.perform(
                post("/CLIMBING/market/{id}/addToWishList", "Test")
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(view().name("redirect:"));

        Mockito.verify(userService, times(1)).addToWishList("Test", "Vas");

    }

    @Test
    @WithMockUser(username = "Vas", roles = {"USER", "ADMIN"})
    public void testDelete() throws Exception {

        mockMvc.perform(
                delete("/CLIMBING/market/{id}/delete", "Test")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/CLIMBING/market"));

        Mockito.verify(offerService,times(1)).deleteOffer("Test");

    }


}

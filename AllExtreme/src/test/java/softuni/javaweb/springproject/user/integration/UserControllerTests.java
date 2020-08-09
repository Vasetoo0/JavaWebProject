package softuni.javaweb.springproject.user.integration;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import softuni.javaweb.springproject.enums.Sport;
import softuni.javaweb.springproject.offer.model.view.AllOfferViewModel;
import softuni.javaweb.springproject.offer.service.OfferService;
import softuni.javaweb.springproject.user.model.view.UserViewModel;
import softuni.javaweb.springproject.user.service.UserService;
import softuni.javaweb.springproject.utils.cloudinary.service.CloudinaryService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;
    @MockBean
    CloudinaryService cloudinaryService;
    @MockBean
    OfferService offerService;
    @MockBean
    ModelMapper modelMapper;

    @Test
    public void testLoginView() throws Exception {

        mockMvc.perform(get("/users/login"))
                .andExpect(view().name("user/login"))
                .andExpect(status().isOk());
    }

    @Test
    public void testLoginError() throws Exception {

        mockMvc.perform(post("/users/login-error")
        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("user/login"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attributeExists("username"));
    }

    @Test
    public void testRegisterView() throws Exception {

        mockMvc.perform(get("/users/register"))
                .andExpect(view().name("user/register"))
                .andExpect(status().isOk());
    }

    @Test
    public void testRegisterConfirm() throws Exception {
        when(userService.existsUser("Test")).thenReturn(false);
        when(userService.existsEmail("Test")).thenReturn(false);

        mockMvc.perform(post("/users/register")
        .with(csrf())
        .param("username","Test")
        .param("email", "asd@asd.asd")
        .param("password","12345")
        .param("confirmPassword","12345"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:login"));

    }

    @Test
    public void testRegisterConfirmShouldRedirectBackIfUserExist() throws Exception {
        when(userService.existsUser("Test")).thenReturn(true);
        when(userService.existsEmail("Test")).thenReturn(false);

        mockMvc.perform(post("/users/register")
                .with(csrf())
                .param("username","Test")
                .param("email", "asd@asd.asd")
                .param("password","12345")
                .param("confirmPassword","12345"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:register"));

    }

    @Test
    public void testRegisterConfirmShouldRedirectBackIfEmailExist() throws Exception {
        when(userService.existsUser("Test")).thenReturn(false);
        when(userService.existsEmail("asd@asd.asd")).thenReturn(true);

        mockMvc.perform(post("/users/register")
                .with(csrf())
                .param("username","Test")
                .param("email", "asd@asd.asd")
                .param("password","12345")
                .param("confirmPassword","12345"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:register"));

    }

    @Test
    public void testRegisterConfirmShouldRedirectBackIfInvalidInput() throws Exception {
        when(userService.existsUser("Test")).thenReturn(false);
        when(userService.existsEmail("asd@asd.asd")).thenReturn(false);

        mockMvc.perform(post("/users/register")
                .with(csrf())
                .param("username","T")
                .param("email", "asdasd.asd")
                .param("password","12345")
                .param("confirmPassword","12"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:register"));

    }

    @Test
    @WithMockUser
    public void testProfileView() throws Exception {
        UserViewModel user = new UserViewModel();
        user.setUsername("Test");

        when(userService.getUserViewByUsername("Test")).thenReturn(user);

        mockMvc.perform(get("/users/{name}", "Test"))
                .andExpect(view().name("user/profile"))
                .andExpect(model().attributeExists("user"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void testMyOffersView() throws Exception {
        when(userService.existsUser("Test")).thenReturn(true);

        mockMvc.perform(get("/users/{name}/myOffers","Test"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/my-offers"))
                .andExpect(model().attributeExists("myOffers"));
    }

    @Test
    @WithMockUser
    public void testMyOffersViewShouldThrowIfNoUser() throws Exception {
        when(userService.existsUser("Test")).thenReturn(false);

        mockMvc.perform(get("/users/{name}/myOffers","Test"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser
    public void testAddOfferView() throws Exception {
        when(userService.existsUser("Test")).thenReturn(true);

        mockMvc.perform(get("/users/{name}/addOffer","Test"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/add-offer"))
                .andExpect(model().attributeExists("offerAddBindingModel"));
    }

    @Test
    @WithMockUser
    public void testAddOfferViewShouldThrow() throws Exception {
        when(userService.existsUser("Test")).thenReturn(false);

        mockMvc.perform(get("/users/{name}/addOffer","Test"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser
    public void testGetWishList() throws Exception {
        AllOfferViewModel allOfferViewModel = new AllOfferViewModel();
        allOfferViewModel.setPictures(List.of("Pic"));
        allOfferViewModel.setSport(Sport.CLIMBING);

        when(userService.getWishList("Test")).thenReturn(List.of(allOfferViewModel));

        mockMvc.perform(get("/users/{name}/wishList","Test"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/wish-list"))
                .andExpect(model().attributeExists("wishList"));
    }
}

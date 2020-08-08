package softuni.javaweb.springproject.user.integration;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import softuni.javaweb.springproject.offer.service.OfferService;
import softuni.javaweb.springproject.user.model.view.UserViewModel;
import softuni.javaweb.springproject.user.service.UserService;
import softuni.javaweb.springproject.utils.cloudinary.service.CloudinaryService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    public void testRegisterView() throws Exception {

        mockMvc.perform(get("/users/register"))
                .andExpect(view().name("user/register"))
                .andExpect(status().isOk());
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
}

package softuni.javaweb.springproject.customerRequests.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import softuni.javaweb.springproject.help.service.RequestService;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class HelpControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    RequestService requestService;

    @Test
    public void testFAQView() throws Exception {

        mockMvc.perform(get("/help/FAQ"))
                .andExpect(view().name("help/FAQ"))
                .andExpect(status().isOk());
    }

    @Test
    public void testAboutView() throws Exception {

        mockMvc.perform(get("/help/team"))
                .andExpect(view().name("help/about-us"))
                .andExpect(status().isOk());
    }

    @Test
    public void testContactUsView() throws Exception {

        mockMvc.perform(get("/help/contact"))
                .andExpect(view().name("help/contact"))
                .andExpect(model().attributeExists("requestAddBindingModel"))
                .andExpect(status().isOk());
    }

    @Test
    public void testContactUsConfirmFailed() throws Exception {

        mockMvc.perform(post("/help/request")
        .with(csrf())
        .param("name", "")
        .param("email","")
        .param("subject", "")
        .param("message",""))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:contact"));

    }

    @Test
    public void testContactUsConfirm() throws Exception {

        mockMvc.perform(post("/help/request")
                .with(csrf())
                .param("name", "Vaseto")
                .param("email","asdaa@asdasd.asd")
                .param("subject", "Asdkafklasdfkladsklfskl")
                .param("message","sdfafdasljadsjkdsfjkadsfkjdsfjkkjldskjlfsjkl"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:contact"));

    }
}

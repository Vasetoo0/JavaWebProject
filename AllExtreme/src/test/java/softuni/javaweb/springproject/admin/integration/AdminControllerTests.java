package softuni.javaweb.springproject.admin.integration;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;
import softuni.javaweb.springproject.admin.web.AdminController;
import softuni.javaweb.springproject.destination.service.DestinationService;
import softuni.javaweb.springproject.enums.Sport;
import softuni.javaweb.springproject.event.service.EventService;
import softuni.javaweb.springproject.findStore.service.FindStoreService;
import softuni.javaweb.springproject.help.service.RequestService;
import softuni.javaweb.springproject.offer.service.OfferService;
import softuni.javaweb.springproject.story.service.StoryService;
import softuni.javaweb.springproject.utils.cloudinary.service.CloudinaryService;
import softuni.javaweb.springproject.video.service.VideoService;

import java.sql.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = AdminController.class)
@AutoConfigureMockMvc
public class AdminControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private StoryService storyService;
    @MockBean
    private RequestService requestService;
    @MockBean
    private VideoService videoService;
    @MockBean
    private EventService eventService;
    @MockBean
    private OfferService offerService;
    @MockBean
    private CloudinaryService cloudinaryService;
    @MockBean
    private FindStoreService findStoreService;
    @MockBean
    private DestinationService destinationService;
    @MockBean
    ModelMapper modelMapper;

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void testAddStoryView() throws Exception {

        mockMvc.perform(get("/admin/addStory"))
                .andExpect(view().name("admin/add-story"))
                .andExpect(model().attributeExists("storyAddBindingModel"))
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void testAddVideoView() throws Exception {

        mockMvc.perform(get("/admin/addVideo"))
                .andExpect(view().name("admin/add-video"))
                .andExpect(model().attributeExists("videoAddBindingModel"))
                .andExpect(status().isOk());
    }

//    @Test
//    @WithMockUser(roles = {"ADMIN"})
//    public void testAddVideoFailed() throws Exception {
//
//        mockMvc.perform(post("/admin/addVideo")
//                .with(csrf())
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .param("title", "")
//                .param("youTubeLink","")
//                .param("sport",Sport.CLIMBING.name())
//                .param("description", ""))
//                        .andExpect(status().is3xxRedirection())
//                        .andExpect(view().name("redirect:addVideo"));
//    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void testAddDestinationView() throws Exception {

        mockMvc.perform(get("/admin/addDestination"))
                .andExpect(view().name("admin/add-destination"))
                .andExpect(model().attributeExists("destinationAddBindingModel"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void testAddEventView() throws Exception {

        mockMvc.perform(get("/admin/addEvent"))
                .andExpect(view().name("admin/add-event"))
                .andExpect(model().attributeExists("eventAddBindingModel"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void testApproveOffersView() throws Exception {

        mockMvc.perform(get("/admin/approve"))
                .andExpect(view().name("admin/approve-new-offer"))
                .andExpect(model().attributeExists("unApproved"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void testRequestsView() throws Exception {

        mockMvc.perform(get("/admin/requests"))
                .andExpect(view().name("admin/requests"))
                .andExpect(model().attributeExists("userRequests"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void testAddStoreView() throws Exception {

        mockMvc.perform(get("/admin/addStore"))
                .andExpect(view().name("admin/add-store"))
                .andExpect(model().attributeExists("storeAddBindingModel"))
                .andExpect(status().isOk());
    }
}

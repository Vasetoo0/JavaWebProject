package softuni.javaweb.springproject.video.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import softuni.javaweb.springproject.video.service.VideoService;
import softuni.javaweb.springproject.video.web.VideoController;

import javax.persistence.EntityNotFoundException;

import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class VideoControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    VideoService videoService;

    @Test
    @WithMockUser
    public void testGetVideos() throws Exception {
        mockMvc.perform(get("/CLIMBING/videos"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("videos"))
                .andExpect(view().name("videos/videos"));
    }

//    @Test
//    @WithMockUser(roles = {"ADMIN"})
//    public void testDeleteVideo() throws Exception {
//        willThrow(new EntityNotFoundException("Invalid")).given(videoService).deleteById("1234");
//
//        mockMvc.perform(get("/CLIMBING/videos/delete/{id}","1234"))
//                .andExpect();
//    }

}

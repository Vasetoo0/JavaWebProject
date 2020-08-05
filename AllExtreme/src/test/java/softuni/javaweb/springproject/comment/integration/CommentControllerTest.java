package softuni.javaweb.springproject.comment.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;
import softuni.javaweb.springproject.comment.model.service.CommentServiceModel;
import softuni.javaweb.springproject.comment.service.CommentService;
import softuni.javaweb.springproject.comment.web.CommentController;
import softuni.javaweb.springproject.home.HomeController;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = CommentControllerTest.class)
@AutoConfigureMockMvc
public class CommentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @MockBean
    ModelMapper modelMapper;

    @MockBean
    CommentController commentController;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void SetUp() {

        initMocks(this);
    }

//    @Test
//    @WithMockUser(username = "Vas",roles = {"ADMIN","USER"})
//    public void testAddComment() throws Exception {
//        CommentServiceModel cm = new CommentServiceModel();
//        cm.setUsername("Test");
//
//        when(commentService.addComment(new CommentServiceModel(), "1", "Test"))
//                .thenReturn(cm);
//
//        mockMvc.perform(post("/CLIMBING/story/{id}/addComment", "1")
//                .with(csrf())
//                .accept(MediaType.ALL).content(objectMapper.writeValueAsString(cm)).contentType(MediaType.ALL))
//        .andExpect(status().isOk());
//
//    }
}

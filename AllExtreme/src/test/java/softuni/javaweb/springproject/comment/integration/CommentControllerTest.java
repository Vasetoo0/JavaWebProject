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
import softuni.javaweb.springproject.comment.model.entity.Comment;
import softuni.javaweb.springproject.comment.model.service.CommentServiceModel;
import softuni.javaweb.springproject.comment.repository.CommentRepository;
import softuni.javaweb.springproject.comment.service.CommentService;
import softuni.javaweb.springproject.comment.web.CommentController;
import softuni.javaweb.springproject.home.HomeController;
import softuni.javaweb.springproject.story.model.entity.Story;
import softuni.javaweb.springproject.story.repository.StoryRepository;
import softuni.javaweb.springproject.story.service.StoryService;

import java.util.Optional;

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
    CommentService commentService;


    @MockBean
    CommentRepository mockCommentRepository;

    @MockBean
    StoryRepository mockStoryRepository;

//    @Test
//    @WithMockUser
//    public void testAddComment() throws Exception {
//        when(mockCommentRepository.saveAndFlush(any(Comment.class))).thenReturn(new Comment());
//        when(mockStoryRepository.findById("Test")).thenReturn(Optional.of(new Story()));
//
//        mockMvc.perform(post("/story/{id}/addComment","Test")
//        .with(csrf())
//        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//        .param("description", "Yupiiiii!"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(view().name("redirect:/CLIMBING/stories/read/Test"));
//    }
}

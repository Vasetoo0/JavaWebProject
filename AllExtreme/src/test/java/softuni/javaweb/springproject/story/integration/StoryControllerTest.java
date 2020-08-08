package softuni.javaweb.springproject.story.integration;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import softuni.javaweb.springproject.comment.model.view.CommentViewModel;
import softuni.javaweb.springproject.story.model.view.AllStoriesViewModel;
import softuni.javaweb.springproject.story.model.view.StoryViewModel;
import softuni.javaweb.springproject.story.service.StoryService;
import softuni.javaweb.springproject.story.web.StoryController;
import softuni.javaweb.springproject.video.web.VideoController;

import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class StoryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    StoryService mockStoryService;

    @Test
    @WithMockUser
    public void testViewAllStories() throws Exception {
        mockMvc.perform(get("/CLIMBING/stories/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("stories"))
                .andExpect(view().name("stories/stories"));
    }

    @Test
    @WithMockUser
    public void testViewStory() throws Exception {

        StoryViewModel storyViewModel = new StoryViewModel();
        storyViewModel.setTitle("Test");
        storyViewModel.setComments(Set.of(new CommentViewModel()));
        storyViewModel.setPictures(List.of("pic","pic2"));

        AllStoriesViewModel recent = new AllStoriesViewModel();
        recent.setPictures(List.of("pic"));

        when(mockStoryService.getById("1234")).thenReturn(storyViewModel);
        when(mockStoryService.getRecentStories("CLIMBING"))
                .thenReturn(List.of(recent));

        mockMvc.perform(get("/CLIMBING/stories/read/{id}","1234"))
                .andExpect(status().isOk())
                .andExpect(view().name("stories/story-details"))
                .andExpect(model().attributeExists("recent"))
                .andExpect(model().attributeExists("story"));

    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void testDeleteStory() throws Exception {

        mockMvc.perform(get("/CLIMBING/stories/delete/{id}","Test"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/CLIMBING/stories/"));

        Mockito.verify(mockStoryService,times(1)).deleteById("Test");
    }
}

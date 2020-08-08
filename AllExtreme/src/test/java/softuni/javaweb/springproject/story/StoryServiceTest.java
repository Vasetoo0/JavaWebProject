package softuni.javaweb.springproject.story;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import softuni.javaweb.springproject.comment.model.entity.Comment;
import softuni.javaweb.springproject.enums.Sport;
import softuni.javaweb.springproject.findStore.model.entity.Store;
import softuni.javaweb.springproject.story.model.binding.StoryAddBindingModel;
import softuni.javaweb.springproject.story.model.entity.Story;
import softuni.javaweb.springproject.story.model.service.StoryServiceModel;
import softuni.javaweb.springproject.story.model.view.AllStoriesViewModel;
import softuni.javaweb.springproject.story.model.view.StoryViewModel;
import softuni.javaweb.springproject.story.repository.StoryRepository;
import softuni.javaweb.springproject.story.service.StoryService;
import softuni.javaweb.springproject.story.service.impl.StoryServiceImpl;
import softuni.javaweb.springproject.user.service.UserService;

import javax.persistence.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StoryServiceTest {

    private StoryService serviceToTest;

    @Mock
    UserService mockUserService;

    @Mock
    StoryRepository mockStoryRepository;
    private Story story;

    @BeforeEach
    public void setUp(){
        serviceToTest = new StoryServiceImpl(mockStoryRepository,new ModelMapper(),mockUserService);
    }

    @Test
    public void testGetStoriesCount(){
        when(mockStoryRepository.count()).thenReturn(12L);

        Assertions.assertEquals(12,serviceToTest.getStoriesCount());
    }

    @Test
    public void testGetRecentStories(){
        Story story1 = new Story();
        story1.setTitle("Test1");
        story1.setSport(Sport.CLIMBING);
        story1.setCreatedOn(LocalDateTime.now());
        Story story2 = new Story();
        story2.setSport(Sport.CLIMBING);
        story2.setTitle("Test2");
        story2.setCreatedOn(LocalDateTime.of(1999,11,22,22,22));

        when(mockStoryRepository.findAll()).thenReturn(List.of(story1,story2));

        List<AllStoriesViewModel> stories = serviceToTest.getRecentStories("CLIMBING");

        Assertions.assertEquals(1,stories.size());
    }

    @Test
    public void getById(){
        Story story = new Story();
        story.setSport(Sport.CLIMBING);
        story.setTitle("Test");

        when(mockStoryRepository.findById("1")).thenReturn(Optional.of(story));

        StoryViewModel storyViewModel = serviceToTest.getById("1");

        Assertions.assertSame(storyViewModel.getClass(),StoryViewModel.class);
        Assertions.assertEquals(storyViewModel.getTitle(),story.getTitle());
    }

    @Test
    public void testGetByIdShouldThrows() {
        when(mockStoryRepository.findById("Test")).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            serviceToTest.getById("Test");
        });
    }

    @Test
    public void testGetAllBySport(){
        Story story = new Story();
        story.setTitle("Test");
        story.setSport(Sport.CLIMBING);
        Story story1 = new Story();
        story1.setTitle("Test1");
        story1.setSport(Sport.CLIMBING);
        Story story2 = new Story();
        story2.setTitle("Test2");
        story2.setSport(Sport.SNOWBOARDING);

        when(mockStoryRepository.findAll()).thenReturn(List.of(story,story1,story2));

        List<AllStoriesViewModel> stories = serviceToTest.getAllBySport("CLIMBING");

        Assertions.assertEquals(2,stories.size());
        Assertions.assertEquals(stories.get(1).getTitle(),story1.getTitle());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void testAddStory(){
        StoryAddBindingModel storyToAdd = new StoryAddBindingModel();
        storyToAdd.setTitle("Test");
        storyToAdd.setPictures(List.of("pic1","pic2",""));

        Story story = new Story();
        story.setTitle("Test");
        story.setPictures(List.of("pic1","pic2"));


        when(mockStoryRepository.save(any(Story.class))).thenReturn(story);

        StoryServiceModel storyServiceModel = serviceToTest.addStory(storyToAdd);

        Assertions.assertEquals(storyServiceModel.getClass(),StoryServiceModel.class);
        Assertions.assertEquals(storyServiceModel.getTitle(),storyToAdd.getTitle());
        Assertions.assertEquals(2,storyServiceModel.getPictures().size());
    }

    @Test
    public void testAddCommentToStory(){
        Comment commentToSave = new Comment();
        commentToSave.setUser("Test");

        Story storyToAdd = new Story();
        storyToAdd.setComments(new HashSet<>());

        when(mockStoryRepository.findById("Test")).thenReturn(Optional.of(storyToAdd));

        serviceToTest.addCommentToStory(commentToSave,"Test");

        Assertions.assertTrue(storyToAdd.getComments().contains(commentToSave));
        Mockito.verify(mockStoryRepository,times(1)).saveAndFlush(storyToAdd);
    }

    @Test
    public void testAddCommentToStoryShouldThrowIfNoStory(){
        Comment commentToSave = new Comment();
        commentToSave.setUser("Test");

        when(mockStoryRepository.findById("Test")).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class,() -> {
            serviceToTest.addCommentToStory(commentToSave,"Test");
        });
    }

    @Test
    public void testDeleteStory() {

        serviceToTest.deleteById("Test");

        Mockito.verify(mockStoryRepository,times(1)).deleteById("Test");
    }





}

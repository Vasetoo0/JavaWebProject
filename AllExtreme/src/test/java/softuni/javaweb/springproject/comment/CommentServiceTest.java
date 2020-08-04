package softuni.javaweb.springproject.comment;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import softuni.javaweb.springproject.comment.model.entity.Comment;
import softuni.javaweb.springproject.comment.model.service.CommentServiceModel;
import softuni.javaweb.springproject.comment.repository.CommentRepository;
import softuni.javaweb.springproject.comment.service.CommentService;
import softuni.javaweb.springproject.comment.service.impl.CommentServiceImpl;
import softuni.javaweb.springproject.story.service.StoryService;
import softuni.javaweb.springproject.user.service.UserService;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    private CommentService serviceToTest;

    @Mock
    private CommentRepository mockCommentRepository;
    @Mock
    private UserService mockUserService;
    @Mock
    private StoryService mockStoryService;

    @BeforeEach
    public void setUp(){
        serviceToTest = new CommentServiceImpl(mockUserService,new ModelMapper(),mockCommentRepository,mockStoryService);
    }

    @Test
    public void addCommentShouldReturnProperCommentModelClass(){
        Comment savedComment = new Comment();
        savedComment.setCreatedOn(LocalDateTime.now());
        savedComment.setDescription("Test description");
        savedComment.setUser("Test user");
        savedComment.setId("12345");

        CommentServiceModel comment1 = new CommentServiceModel();
        comment1.setCreatedOn(LocalDateTime.now());
        comment1.setDescription("Test description");
        comment1.setUsername("Test user");

        when(mockCommentRepository.saveAndFlush(any(Comment.class))).thenReturn(savedComment);

        CommentServiceModel savedComment1 = serviceToTest.addComment(comment1,"12","Test user");

        Assertions.assertSame(savedComment1.getClass(), CommentServiceModel.class);
        Assertions.assertEquals(savedComment1.getDescription(), comment1.getDescription());
    }
}

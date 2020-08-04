package softuni.javaweb.springproject.comment.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.javaweb.springproject.comment.model.binding.CommentAddBindingModel;
import softuni.javaweb.springproject.comment.model.entity.Comment;
import softuni.javaweb.springproject.comment.model.service.CommentServiceModel;
import softuni.javaweb.springproject.comment.repository.CommentRepository;
import softuni.javaweb.springproject.comment.service.CommentService;
import softuni.javaweb.springproject.story.service.StoryService;
import softuni.javaweb.springproject.user.service.UserService;

import java.time.LocalDateTime;

@Service
public class CommentServiceImpl implements CommentService {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final CommentRepository commentRepository;
    private final StoryService storyService;

    @Autowired
    public CommentServiceImpl(UserService userService, ModelMapper modelMapper, CommentRepository commentRepository, StoryService storyService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.commentRepository = commentRepository;
        this.storyService = storyService;
    }

    @Override
    public CommentServiceModel addComment(CommentServiceModel CommentServiceModel, String storyId, String username) {

        Comment comment = this.modelMapper.map(
                CommentServiceModel, Comment.class
        );

        comment.setUser(username);
        comment.setCreatedOn(LocalDateTime.now());

        Comment savedComment = this.commentRepository.saveAndFlush(comment);

        this.storyService.addCommentToStory(savedComment,storyId);

        return this.modelMapper.map(
                savedComment,CommentServiceModel.class
        );
    }
}

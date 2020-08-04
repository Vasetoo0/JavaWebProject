package softuni.javaweb.springproject.comment.service;


import softuni.javaweb.springproject.comment.model.binding.CommentAddBindingModel;
import softuni.javaweb.springproject.comment.model.service.CommentServiceModel;

public interface CommentService {

    CommentServiceModel addComment(CommentServiceModel CommentServiceModel, String storyId, String username);
}

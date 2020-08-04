package softuni.javaweb.springproject.comment.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import softuni.javaweb.springproject.comment.model.binding.CommentAddBindingModel;
import softuni.javaweb.springproject.comment.model.service.CommentServiceModel;
import softuni.javaweb.springproject.comment.service.CommentService;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class CommentController {

    private final CommentService commentService;
    private final ModelMapper modelMapper;

    @Autowired
    public CommentController(CommentService commentService, ModelMapper modelMapper) {
        this.commentService = commentService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/story/{id}/addComment")
    public String addComment(@PathVariable("id") String storyId, HttpSession httpSession,
                             Principal principal,
                             @ModelAttribute("commentAddBindingModel")CommentAddBindingModel commentAddBindingModel) {

        CommentServiceModel commentServiceModel = this.modelMapper.map(commentAddBindingModel,CommentServiceModel.class);

        this.commentService.addComment(commentServiceModel,storyId,principal.getName());

        return "redirect:/" + httpSession.getAttribute("selectedSport") +"/stories/read/" + storyId;
    }

}

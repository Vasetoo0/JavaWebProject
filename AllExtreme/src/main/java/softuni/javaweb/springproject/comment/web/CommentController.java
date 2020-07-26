package softuni.javaweb.springproject.comment.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import softuni.javaweb.springproject.comment.model.binding.CommentAddBindingModel;
import softuni.javaweb.springproject.comment.service.CommentService;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/story/{id}/addComment")
    public String addComment(@PathVariable("id") String storyId, HttpSession httpSession,
                             Principal principal,
                             @ModelAttribute("commentAddBindingModel")CommentAddBindingModel commentAddBindingModel) {

        this.commentService.addComment(commentAddBindingModel,storyId,principal.getName());

        return "redirect:/" + httpSession.getAttribute("selectedSport") +"/stories/" + storyId;
    }

}

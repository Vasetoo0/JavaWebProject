package softuni.javaweb.springproject.story.service;

import softuni.javaweb.springproject.comment.model.entity.Comment;
import softuni.javaweb.springproject.story.model.binding.StoryAddBindingModel;
import softuni.javaweb.springproject.story.model.service.StoryServiceModel;
import softuni.javaweb.springproject.story.model.view.AllStoriesViewModel;
import softuni.javaweb.springproject.story.model.view.StoryViewModel;

import java.util.List;

public interface StoryService {

    StoryServiceModel addStory(StoryAddBindingModel storyAddBindingModel);

    List<AllStoriesViewModel> getAllBySport(String selectedSport);

    StoryViewModel getById(String id);

    List<AllStoriesViewModel> getRecentStories(String sport);

    void addCommentToStory(Comment savedComment, String storyId);

    void deleteById(String id);

    Long getStoriesCount();
}

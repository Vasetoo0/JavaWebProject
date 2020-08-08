package softuni.javaweb.springproject.story.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import softuni.javaweb.springproject.comment.model.entity.Comment;
import softuni.javaweb.springproject.story.model.binding.StoryAddBindingModel;
import softuni.javaweb.springproject.story.model.entity.Story;
import softuni.javaweb.springproject.story.model.service.StoryServiceModel;
import softuni.javaweb.springproject.story.model.view.AllStoriesViewModel;
import softuni.javaweb.springproject.story.model.view.StoryViewModel;
import softuni.javaweb.springproject.story.repository.StoryRepository;
import softuni.javaweb.springproject.story.service.StoryService;
import softuni.javaweb.springproject.user.service.UserService;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoryServiceImpl implements StoryService {

    private final StoryRepository storyRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    @Autowired
    public StoryServiceImpl(StoryRepository storyRepository, ModelMapper modelMapper, UserService userService) {
        this.storyRepository = storyRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public StoryServiceModel addStory(StoryAddBindingModel storyAddBindingModel) {
        StoryServiceModel storyServiceModel = this.modelMapper.map(
                storyAddBindingModel, StoryServiceModel.class
        );

        storyServiceModel.setPictures(storyAddBindingModel.getPictures()
                .stream()
                .filter(p -> !p.isEmpty() && !p.isBlank())
                .collect(Collectors.toList()));

        Story story = this.modelMapper.map(
                storyServiceModel, Story.class
        );

        return this.modelMapper.map(
                this.storyRepository.save(story), StoryServiceModel.class
        );
    }

    @Override
    public List<AllStoriesViewModel> getAllBySport(String selectedSport) {

        return this.storyRepository.findAll()
                .stream()
                .filter(s -> s.getSport().name().equals(selectedSport))
                .map(s -> this.modelMapper.map(s, AllStoriesViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public StoryViewModel getById(String id) {

        return this.storyRepository.findById(id)
                .map(s -> this.modelMapper.map(s, StoryViewModel.class))
                .orElseThrow(() ->
                        new EntityNotFoundException("By some reason story is not found. We will check thanks for your patients!"));
    }

    @Override
    public List<AllStoriesViewModel> getRecentStories(String sport) {
        return this.storyRepository.findAll()
                .stream()
                .filter(s -> s.getCreatedOn().
                        isAfter(LocalDateTime.now().minus(15, ChronoUnit.DAYS)))
                .filter(s -> s.getSport().name().equals(sport))
                .map(s -> this.modelMapper.map(s,AllStoriesViewModel.class))
                .limit(5)
                .collect(Collectors.toList());
    }

    @Override
    public void addCommentToStory(Comment savedComment, String storyId) {
        Story storyById = this.storyRepository.findById(storyId)
                .orElseThrow(() -> new EntityNotFoundException("No story to add comment to!"));

        storyById.getComments().add(savedComment);

        this.storyRepository.saveAndFlush(storyById);
    }

    @Override
    public void deleteById(String id) {
        this.storyRepository.deleteById(id);
    }

    @Override
    public Long getStoriesCount() {

        return this.storyRepository.count();
    }
}

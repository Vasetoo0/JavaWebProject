package softuni.javaweb.springproject.story.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.javaweb.springproject.story.repository.StoryRepository;
import softuni.javaweb.springproject.story.service.StoryService;
import softuni.javaweb.springproject.user.service.UserService;

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


}

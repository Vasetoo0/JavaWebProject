package softuni.javaweb.springproject.video.service;

import softuni.javaweb.springproject.video.model.binding.VideoAddBindingModel;
import softuni.javaweb.springproject.video.model.service.VideoServiceModel;
import softuni.javaweb.springproject.video.model.view.VideoViewModel;

import java.util.List;

public interface VideoService {


    List<VideoViewModel> getBySport(String sport);

    VideoServiceModel addVideo(VideoAddBindingModel videoAddBindingModel);

    void deleteById(String id);

    Long getVideosCount();
}

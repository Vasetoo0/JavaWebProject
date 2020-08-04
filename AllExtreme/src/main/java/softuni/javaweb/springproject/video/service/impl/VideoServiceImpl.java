package softuni.javaweb.springproject.video.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.javaweb.springproject.video.model.binding.VideoAddBindingModel;
import softuni.javaweb.springproject.video.model.entity.Video;
import softuni.javaweb.springproject.video.model.service.VideoServiceModel;
import softuni.javaweb.springproject.video.model.view.VideoViewModel;
import softuni.javaweb.springproject.video.repository.VideoRepository;
import softuni.javaweb.springproject.video.service.VideoService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public VideoServiceImpl(VideoRepository videoRepository, ModelMapper modelMapper) {
        this.videoRepository = videoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<VideoViewModel> getBySport(String sport) {

        return this.videoRepository.findAll()
                .stream()
                .filter(v -> v.getSport().name().equals(sport))
                .map(v -> this.modelMapper.map(v, VideoViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public VideoServiceModel addVideo(VideoAddBindingModel videoAddBindingModel) {

        VideoServiceModel videoServiceModel = this.modelMapper
                .map(videoAddBindingModel,VideoServiceModel.class);

        return this.modelMapper
                .map(this.videoRepository.save(this.modelMapper.map(videoServiceModel,Video.class)),
                VideoServiceModel.class);
    }

    //TODO: Test!
    @Override
    public void deleteById(String id) {
        this.videoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Video not found"));

        this.videoRepository.deleteById(id);
    }

    @Override
    public Long getVideosCount() {
        return this.videoRepository.count();
    }
}

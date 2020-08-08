package softuni.javaweb.springproject.video;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import softuni.javaweb.springproject.enums.Sport;
import softuni.javaweb.springproject.video.model.binding.VideoAddBindingModel;
import softuni.javaweb.springproject.video.model.entity.Video;
import softuni.javaweb.springproject.video.model.service.VideoServiceModel;
import softuni.javaweb.springproject.video.model.view.VideoViewModel;
import softuni.javaweb.springproject.video.repository.VideoRepository;
import softuni.javaweb.springproject.video.service.VideoService;
import softuni.javaweb.springproject.video.service.impl.VideoServiceImpl;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(MockitoExtension.class)
public class VideoServiceTest {

    private VideoService serviceToTest;

    @Mock
    public VideoRepository mockVideoRepository;

    @BeforeEach
    public void setUp() {
        serviceToTest = new VideoServiceImpl(mockVideoRepository,new ModelMapper());
    }


    @Test
    public void getBySportShouldReturnListOfVideoViewModelsOfGivenSport() {
        Video video = new Video();
        video.setSport(Sport.CLIMBING);
        video.setTitle("Test 1");
        video.setYouTubeLink("test");
        video.setDescription("Test");
        video.setId("12345");
        Video video1 = new Video();
        video1.setSport(Sport.CLIMBING);
        video1.setTitle("Test 2");
        Video video2 = new Video();
        video2.setSport(Sport.SNOWBOARDING);

        when(mockVideoRepository.findAll()).thenReturn(List.of(video,video1,video2));

        List<VideoViewModel> videos = this.serviceToTest.getBySport("CLIMBING");

        VideoViewModel firstVideo = videos.get(0);

        Assertions.assertEquals(2,videos.size());
        Assertions.assertEquals(firstVideo.getTitle(),video.getTitle());
    }

    @Test
    public void testGetVideosCount(){
        when(mockVideoRepository.count()).thenReturn(2L);

        Assertions.assertEquals(2,serviceToTest.getVideosCount());
    }

    @Test
    public void testAddVideo(){
        Video savedVideo = new Video();
        savedVideo.setSport(Sport.CLIMBING);
        savedVideo.setTitle("Test");
        savedVideo.setId("Test id");

        VideoAddBindingModel videoBinding = new VideoAddBindingModel();
        videoBinding.setTitle("Test");

        when(mockVideoRepository.save(any(Video.class))).thenReturn(savedVideo);

        VideoServiceModel videoServiceModel = serviceToTest.addVideo(videoBinding);

        Assertions.assertSame(videoServiceModel.getClass(),VideoServiceModel.class);
        Assertions.assertEquals(videoServiceModel.getTitle(),videoBinding.getTitle());
    }

    @Test
    public void testDeleteVideoThrows() {
        when(mockVideoRepository.findById("1")).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            serviceToTest.deleteById("1");
        });
    }

    @Test
    public void testDeleteVideo() {
        when(mockVideoRepository.findById("1")).thenReturn(Optional.of(new Video()));

        serviceToTest.deleteById("1");

        Mockito.verify(mockVideoRepository,times(1)).deleteById("1");
    }


}

package softuni.javaweb.springproject.video.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.javaweb.springproject.video.model.entity.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video,String> {


}

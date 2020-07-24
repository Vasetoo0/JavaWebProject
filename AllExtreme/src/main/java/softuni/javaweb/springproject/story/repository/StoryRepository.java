package softuni.javaweb.springproject.story.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.javaweb.springproject.story.model.entity.Story;

@Repository
public interface StoryRepository extends JpaRepository<Story,String> {
}

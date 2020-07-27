package softuni.javaweb.springproject.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.javaweb.springproject.event.model.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event,String> {
}

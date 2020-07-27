package softuni.javaweb.springproject.destination.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.javaweb.springproject.destination.model.entity.Destination;

@Repository
public interface DestinationRepository extends JpaRepository<Destination,String> {


}

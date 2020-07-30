package softuni.javaweb.springproject.help.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.javaweb.springproject.help.model.entity.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request,String> {


}

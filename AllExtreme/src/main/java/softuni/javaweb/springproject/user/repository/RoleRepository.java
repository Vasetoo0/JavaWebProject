package softuni.javaweb.springproject.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.javaweb.springproject.user.model.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {


    Role findByAuthority(String role);
}

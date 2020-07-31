package softuni.javaweb.springproject.findStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.javaweb.springproject.findStore.model.entity.Store;

@Repository
public interface FindStoreRepository extends JpaRepository<Store,String> {
}

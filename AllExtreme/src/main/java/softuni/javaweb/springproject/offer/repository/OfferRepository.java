package softuni.javaweb.springproject.offer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.javaweb.springproject.offer.model.entity.Offer;

import java.time.Instant;
import java.time.LocalDateTime;

@Repository
public interface OfferRepository extends JpaRepository<Offer,String> {
    void deleteByCreatedOnBefore(LocalDateTime createdOn);
}

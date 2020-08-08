package softuni.javaweb.springproject.offer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import softuni.javaweb.springproject.enums.Sport;
import softuni.javaweb.springproject.offer.model.binding.OfferAddBindingModel;
import softuni.javaweb.springproject.offer.model.entity.Offer;
import softuni.javaweb.springproject.offer.model.service.OfferServiceModel;
import softuni.javaweb.springproject.offer.model.view.AllOfferViewModel;
import softuni.javaweb.springproject.offer.model.view.OfferViewModel;
import softuni.javaweb.springproject.offer.model.view.UnApprovedOfferViewModel;
import softuni.javaweb.springproject.offer.repository.OfferRepository;
import softuni.javaweb.springproject.offer.service.OfferService;
import softuni.javaweb.springproject.offer.service.impl.OfferServiceImpl;
import softuni.javaweb.springproject.user.model.entity.UserEntity;
import softuni.javaweb.springproject.user.service.UserService;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OfferServiceTest {

    private OfferService serviceToTest;

    @Mock
    UserService mockUserService;

    @Mock
    OfferRepository mockOfferRepository;

    @BeforeEach
    public void setUp() {
        serviceToTest = new OfferServiceImpl(mockUserService,mockOfferRepository,new ModelMapper());
    }

    @Test
    public void testGetOffersCount(){
        when(mockOfferRepository.count()).thenReturn(10L);

        Assertions.assertEquals(10,serviceToTest.getOffersCount());
    }

    @Test
    public void testGetById(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("Test");

        Offer offer = new Offer();
        offer.setTitle("Test");
        offer.setCreator(userEntity);

        when(mockOfferRepository.findById("1")).thenReturn(Optional.of(offer));

        OfferViewModel returnedOffer = serviceToTest.getById("1");

        Assertions.assertSame(returnedOffer.getClass(),OfferViewModel.class);
        Assertions.assertEquals(offer.getTitle(),returnedOffer.getTitle());
        Assertions.assertEquals(offer.getCreator().getUsername(),returnedOffer.getCreator());
    }

    @Test
    public void testGetByIdThrowsError() {
        when(mockOfferRepository.findById("Test")).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            serviceToTest.getById("Test");
        });
    }

    @Test
    public void testGetAllBySport(){
        Offer offer = new Offer();
        offer.setTitle("Test");
        offer.setEnabled(true);
        offer.setSport(Sport.CLIMBING);

        Offer offer1 = new Offer();
        offer1.setTitle("Test1");
        offer1.setEnabled(false);
        offer1.setSport(Sport.CLIMBING);

        Offer offer2 = new Offer();
        offer2.setTitle("Test2");
        offer2.setEnabled(true);
        offer2.setSport(Sport.SNOWBOARDING);

        when(mockOfferRepository.findAll()).thenReturn(List.of(offer,offer1,offer2));

        List<AllOfferViewModel> returnedOffers = serviceToTest.getAllBySport("CLIMBING");

        Assertions.assertEquals(1,returnedOffers.size());
        Assertions.assertEquals(returnedOffers.get(0).getTitle(),offer.getTitle());

    }

    @Test
    public void testGetUnApprovedOffers(){
        Offer offer = new Offer();
        offer.setTitle("Test");
        offer.setEnabled(true);

        Offer offer1 = new Offer();
        offer1.setTitle("Test1");
        offer1.setEnabled(false);

        Offer offer2 = new Offer();
        offer2.setTitle("Test2");
        offer2.setEnabled(false);

        when(mockOfferRepository.findAll()).thenReturn(List.of(offer,offer1,offer2));

        List<UnApprovedOfferViewModel> returnedUnApprovedOffers = serviceToTest.getUnApproved();

        Assertions.assertEquals(2,returnedUnApprovedOffers.size());
        Assertions.assertEquals(offer1.getTitle(),returnedUnApprovedOffers.get(0).getTitle());
        Assertions.assertEquals(offer2.getTitle(),returnedUnApprovedOffers.get(1).getTitle());
    }

    @Test
    public void testGetByCreator(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("Test");
        UserEntity userEntity1 = new UserEntity();
        userEntity1.setUsername("Test1");

        Offer offer = new Offer();
        offer.setTitle("Test");
        offer.setEnabled(true);
        offer.setCreator(userEntity);

        Offer offer1 = new Offer();
        offer1.setTitle("Test1");
        offer1.setEnabled(false);
        offer1.setCreator(userEntity);

        Offer offer2 = new Offer();
        offer2.setTitle("Test2");
        offer2.setEnabled(false);
        offer2.setCreator(userEntity1);

        when(mockOfferRepository.findAll()).thenReturn(List.of(offer,offer1,offer2));

        List<AllOfferViewModel> returnedByCreator = serviceToTest.getByCreator("Test");

        Assertions.assertEquals(2,returnedByCreator.size());
        Assertions.assertEquals(returnedByCreator.get(0).getTitle(),offer.getTitle());
        Assertions.assertEquals(returnedByCreator.get(1).getTitle(),offer1.getTitle());
    }

    @Test
    public void testAddOffer(){
        OfferAddBindingModel offerAddBindingModel = new OfferAddBindingModel();
        offerAddBindingModel.setTitle("Test");
        offerAddBindingModel.setCreator("Test");

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("Test");

        Offer offer = new Offer();
        offer.setTitle("Test");
        offer.setCreator(userEntity);

        when(mockOfferRepository.saveAndFlush(any(Offer.class))).thenReturn(offer);

        OfferServiceModel offerServiceModel = serviceToTest.addOffer(offerAddBindingModel);

        Assertions.assertEquals(offerServiceModel.getClass(),OfferServiceModel.class);
    }

    @Test
    public void testCleanUpOldOffer() {

        serviceToTest.cleanUpOldOffer();

        Mockito.verify(mockOfferRepository, times(1))
                .deleteByCreatedOnBefore(any(LocalDateTime.class));
    }

    @Test
    public void testDeleteOffer() {

        serviceToTest.deleteOffer("Test");

        Mockito.verify(mockOfferRepository, times(1))
                .deleteById("Test");
    }

    @Test
    public void testApproveOfferThrowsIfNoOffer() {
        when(mockOfferRepository.findById("Test")).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            serviceToTest.approveOffer("Test");
        });
    }
}

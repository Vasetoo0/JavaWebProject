package softuni.javaweb.springproject.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import softuni.javaweb.springproject.offer.model.entity.Offer;
import softuni.javaweb.springproject.offer.model.view.AllOfferViewModel;
import softuni.javaweb.springproject.offer.model.view.OfferViewModel;
import softuni.javaweb.springproject.offer.service.OfferService;
import softuni.javaweb.springproject.user.model.entity.UserEntity;
import softuni.javaweb.springproject.user.model.service.UserServiceModel;
import softuni.javaweb.springproject.user.repository.UserRepository;
import softuni.javaweb.springproject.user.service.RoleService;
import softuni.javaweb.springproject.user.service.UserService;
import softuni.javaweb.springproject.user.service.impl.UserServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    private UserService serviceToTest;

    @Mock
    UserRepository mockUserRepository;

    @Mock
    OfferService mockOfferService;

    @Mock
    RoleService mockRoleService;

    @Mock
    BCryptPasswordEncoder mockPasswordEncoder;

    @BeforeEach
    public void setUp(){
        serviceToTest = new UserServiceImpl(mockUserRepository,mockOfferService,mockRoleService,new ModelMapper(),mockPasswordEncoder);
    }

    @Test
    public void testExistUser() {

        when(mockUserRepository.findByUsername("Test")).thenReturn(Optional.of(new UserEntity()));

        Assertions.assertTrue(serviceToTest.existsUser("Test"));
        Assertions.assertFalse(serviceToTest.existsUser("Test1"));
    }

    @Test
    public void testExistEmail() {

        when(mockUserRepository.findByEmail("Test")).thenReturn(Optional.of(new UserEntity()));

        Assertions.assertTrue(serviceToTest.existsEmail("Test"));
        Assertions.assertFalse(serviceToTest.existsEmail("Test1"));
    }

    @Test
    public void testGetByUsername() {

        when(mockUserRepository.findByUsername("Test")).thenReturn(Optional.of(new UserEntity()));

        Assertions.assertNotNull(serviceToTest.getByUsername("Test"));
        Assertions.assertSame(UserEntity.class,serviceToTest.getByUsername("Test").getClass());
    }

    @Test
    public void testGetWishList() {
        UserEntity userEntity = new UserEntity();
        userEntity.setWishList(Set.of("wish"));

        OfferViewModel offer = new OfferViewModel();
        offer.setTitle("Test");

        when(mockUserRepository.findByUsername("Test")).thenReturn(Optional.of(userEntity));
        when(mockOfferService.getById("wish")).thenReturn(offer);

        List<AllOfferViewModel> returned = serviceToTest.getWishList("Test");

        Assertions.assertEquals(1,returned.size());
        Assertions.assertEquals(offer.getTitle(),returned.get(0).getTitle());
    }

    @Test
    public void testCheckIfExistInWishList() {
        UserEntity user = new UserEntity();
        user.setWishList(Set.of("wish"));

        when(mockUserRepository.findByUsername("Test")).thenReturn(Optional.of(user));

        Assertions.assertTrue(serviceToTest.checkIfExistInWishList("Test","wish"));
        Assertions.assertFalse(serviceToTest.checkIfExistInWishList("Test","wish1"));
    }

    @Test
    public void testGetUsersCount() {
        when(mockUserRepository.count()).thenReturn(10L);

        Assertions.assertEquals(10,serviceToTest.getUsersCount());
    }
}

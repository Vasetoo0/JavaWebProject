package softuni.javaweb.springproject.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import softuni.javaweb.springproject.offer.model.entity.Offer;
import softuni.javaweb.springproject.offer.model.view.AllOfferViewModel;
import softuni.javaweb.springproject.offer.model.view.OfferViewModel;
import softuni.javaweb.springproject.offer.service.OfferService;
import softuni.javaweb.springproject.user.model.entity.UserEntity;
import softuni.javaweb.springproject.user.model.service.UserServiceModel;
import softuni.javaweb.springproject.user.model.view.UserViewModel;
import softuni.javaweb.springproject.user.repository.UserRepository;
import softuni.javaweb.springproject.user.service.RoleService;
import softuni.javaweb.springproject.user.service.UserService;
import softuni.javaweb.springproject.user.service.impl.UserServiceImpl;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
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
    public void setUp() {
        serviceToTest = new UserServiceImpl(mockUserRepository, mockOfferService, mockRoleService, new ModelMapper(), mockPasswordEncoder);
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
        Assertions.assertSame(UserEntity.class, serviceToTest.getByUsername("Test").getClass());
    }

    @Test
    public void testGetByUsernameThrowsError() {

        when(mockUserRepository.findByUsername("Test")).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class,() -> {
            serviceToTest.getByUsername("Test");
        });
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

        Assertions.assertEquals(1, returned.size());
        Assertions.assertEquals(offer.getTitle(), returned.get(0).getTitle());
    }

    @Test
    public void testCheckIfExistInWishList() {
        UserEntity user = new UserEntity();
        user.setWishList(Set.of("wish"));

        when(mockUserRepository.findByUsername("Test")).thenReturn(Optional.of(user));

        Assertions.assertTrue(serviceToTest.checkIfExistInWishList("Test", "wish"));
        Assertions.assertFalse(serviceToTest.checkIfExistInWishList("Test", "wish1"));
    }

    @Test
    public void testCheckIfExistInWishListThrowsError() {


        when(mockUserRepository.findByUsername("Test")).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class,() -> {
            serviceToTest.addToWishList("123","Test");
        });
    }

    @Test
    public void testGetUsersCount() {
        when(mockUserRepository.count()).thenReturn(10L);

        Assertions.assertEquals(10, serviceToTest.getUsersCount());
    }

    @Test
    public void testFindByUsername() {
        UserEntity user = new UserEntity();
        user.setUsername("Test");
        UserEntity user1 = new UserEntity();
        user1.setUsername("Test1");
        UserEntity user2 = new UserEntity();
        user2.setUsername("Test2");

        when(mockUserRepository.findAll()).thenReturn(List.of(user,user1,user2));

        List<UserViewModel> returned = serviceToTest.findByUserName("Test");

        Assertions.assertEquals(returned.size(),1);
        Assertions.assertEquals(returned.get(0).getUsername(),user.getUsername());

    }

    @Test
    public void testRegisterUser() {
        UserServiceModel user = new UserServiceModel();
        user.setUsername("Test");

        UserEntity savedUser = new UserEntity();
        savedUser.setUsername("Test");

        when(mockUserRepository.save(any(UserEntity.class))).thenReturn(savedUser);

        UserServiceModel returned = serviceToTest.registerUser(user);

        Assertions.assertEquals(UserServiceModel.class,returned.getClass());
        Assertions.assertEquals(savedUser.getUsername(),returned.getUsername());
    }

    @Test
    public void testAddToWishList() {
        UserEntity user = new UserEntity();
        user.setWishList(new HashSet<>());
        when(mockUserRepository.findByUsername("Test")).thenReturn(Optional.of(user));

        serviceToTest.addToWishList("123","Test");

        Mockito.verify(mockUserRepository,times(1)).saveAndFlush(user);
    }

    @Test
    public void testLoadUserByUsername(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("Test");
        userEntity.setAuthorities(new HashSet<>());

        when(mockUserRepository.findByUsername("Test")).thenReturn(Optional.of(userEntity));

        UserDetails user = serviceToTest.loadUserByUsername("Test");

        Assertions.assertEquals(user.getUsername(),userEntity.getUsername());
    }

    @Test
    public void testLoadUserByUsernameThrowsError(){

        when(mockUserRepository.findByUsername("Test")).thenReturn(Optional.empty());

       Assertions.assertThrows(UsernameNotFoundException.class, () -> serviceToTest.loadUserByUsername("Test"));

    }
}

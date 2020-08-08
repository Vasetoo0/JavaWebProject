package softuni.javaweb.springproject.user.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import softuni.javaweb.springproject.offer.model.view.AllOfferViewModel;
import softuni.javaweb.springproject.user.model.entity.UserEntity;
import softuni.javaweb.springproject.user.model.service.UserServiceModel;
import softuni.javaweb.springproject.user.model.view.UserViewModel;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserServiceModel registerUser(UserServiceModel userServiceModel);

    boolean existsUser(String username);

    boolean existsEmail(String email);


    UserEntity getByUsername(String name);

    UserViewModel getUserViewByUsername(String name);

    void addToWishList(String offerId, String userName);

    List<AllOfferViewModel> getWishList(String username);

    boolean checkIfExistInWishList(String name, String offerId);

    Long getUsersCount();

    List<UserViewModel> findByUserName(String username);
}

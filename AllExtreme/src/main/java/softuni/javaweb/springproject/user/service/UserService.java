package softuni.javaweb.springproject.user.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import softuni.javaweb.springproject.user.model.binding.UserRegisterBindingModel;
import softuni.javaweb.springproject.user.model.entity.UserEntity;
import softuni.javaweb.springproject.user.model.service.UserServiceModel;

import javax.validation.Valid;

public interface UserService extends UserDetailsService {

    UserServiceModel registerUser(UserServiceModel userServiceModel);

}

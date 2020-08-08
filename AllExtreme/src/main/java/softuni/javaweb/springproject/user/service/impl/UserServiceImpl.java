package softuni.javaweb.springproject.user.service.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import softuni.javaweb.springproject.offer.model.view.AllOfferViewModel;
import softuni.javaweb.springproject.offer.service.OfferService;
import softuni.javaweb.springproject.user.model.entity.Role;
import softuni.javaweb.springproject.user.model.entity.UserEntity;
import softuni.javaweb.springproject.user.model.service.UserServiceModel;
import softuni.javaweb.springproject.user.model.view.UserViewModel;
import softuni.javaweb.springproject.user.repository.UserRepository;
import softuni.javaweb.springproject.user.service.RoleService;
import softuni.javaweb.springproject.user.service.UserService;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final OfferService offerService;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, @Lazy OfferService offerService, RoleService roleService, ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.offerService = offerService;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean existsUser(String username) {
        Objects.requireNonNull(username);

        return userRepository.findByUsername(username).isPresent();
    }
    public boolean existsEmail(String email) {
        Objects.requireNonNull(email);

        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public UserEntity getByUsername(String name) {
        return this.userRepository.findByUsername(name)
                .orElseThrow(() -> new EntityNotFoundException("User Dont Exist!"));
    }

    @Override
    public UserViewModel getUserViewByUsername(String name) {

        return this.userRepository.findByUsername(name)
                .map(u -> this.modelMapper.map(u,UserViewModel.class))
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));
    }

    @Override
    public void addToWishList(String offerId, String userName) {
        UserEntity userToAddOffer = this.getByUsername(userName);
        userToAddOffer.getWishList().add(offerId);

        this.userRepository.saveAndFlush(userToAddOffer);
    }

    @Override
    public List<AllOfferViewModel> getWishList(String username) {

        List<AllOfferViewModel> wishList = new ArrayList<>();

        this.userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("Such user not exist!"))
                .getWishList().forEach(offerId -> {
                    AllOfferViewModel offer;

                    try {
                        offer = this.modelMapper.map(this.offerService.getById(offerId),AllOfferViewModel.class);
                    } catch (Exception e) {
                        offer = null;
                    }

                    if(offer != null) {
                        wishList.add(offer);
                    }
        });


        return wishList;
    }

    @Override
    public boolean checkIfExistInWishList(String name, String offerId) {

        UserEntity user = this.userRepository.findByUsername(name)
                .orElseThrow(() -> new EntityNotFoundException("No such user!"));


        return  user.getWishList().contains(offerId);
    }

    @Override
    public Long getUsersCount() {
        return this.userRepository.count();
    }

    @Override
    public List<UserViewModel> findByUserName(String username) {

        return this.userRepository.findAll()
                .stream()
                .filter(u -> u.getUsername().equals(username))
                .map(u -> this.modelMapper.map(u,UserViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {

            UserEntity userEntity = this.modelMapper.map(userServiceModel, UserEntity.class);
            if (this.userRepository.count() == 0) {
                userEntity.setAuthorities(this.roleService.getAll());
            } else {
                Role userRole = this.roleService.getByAuthority("ROlE_USER");
                userEntity.setAuthorities(Set.of(userRole));
            }

            userEntity.setPassword(passwordEncoder.encode(userServiceModel.getPassword()));

           return this.modelMapper.map(this.userRepository.save(userEntity), UserServiceModel.class);

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userOpt = this.userRepository.findByUsername(username);

        LOGGER.debug("Trying to load user {}. Successful? {}",
                username, userOpt.isPresent());

        return userOpt
                .map(this::map)
                .orElseThrow(() -> new UsernameNotFoundException("No such user" + username));
    }

    private User map(UserEntity userEntity) {
        List<GrantedAuthority> authorities = userEntity.
                getAuthorities().
                stream().
                map(r -> new SimpleGrantedAuthority(r.getAuthority())).
                collect(Collectors.toList());

        User result = new User(
                userEntity.getUsername(),
                userEntity.getPassword() != null ? userEntity.getPassword() : "",
                authorities);

        //todo - explain
        if (userEntity.getPassword() == null) {
            result.eraseCredentials();
        }

        return result;
    }
}

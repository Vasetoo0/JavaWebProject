package softuni.javaweb.springproject.user.service.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import softuni.javaweb.springproject.user.model.entity.Role;
import softuni.javaweb.springproject.user.model.entity.UserEntity;
import softuni.javaweb.springproject.user.model.service.UserServiceModel;
import softuni.javaweb.springproject.user.repository.UserRepository;
import softuni.javaweb.springproject.user.service.RoleService;
import softuni.javaweb.springproject.user.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {


        //TODO: Validate service model!

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

        User result = new org.springframework.security.core.userdetails.User(
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

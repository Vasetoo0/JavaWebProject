package softuni.javaweb.springproject.user.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.javaweb.springproject.user.model.entity.Role;
import softuni.javaweb.springproject.user.repository.RoleRepository;
import softuni.javaweb.springproject.user.service.RoleService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;

    public RoleServiceImpl(ModelMapper modelMapper, RoleRepository roleRepository) {
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        if (this.roleRepository.count() == 0) {
            Role admin = new Role();
            Role user = new Role();

            admin.setAuthority("ROLE_ADMIN");
            user.setAuthority("ROLE_USER");

            this.roleRepository.save(user);
            this.roleRepository.save(admin);
        }
    }

    @Override
    public Set<Role> getAll() {

       return new HashSet<>(this.roleRepository.findAll());
    }

    @Override
    public Role getByAuthority(String role) {
        return this.roleRepository.findByAuthority(role);
    }
}

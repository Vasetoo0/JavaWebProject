package softuni.javaweb.springproject.user.service;

import softuni.javaweb.springproject.user.model.entity.Role;

import java.util.Set;

public interface RoleService {

    Set<Role> getAll();

    Role getByAuthority(String role);
}

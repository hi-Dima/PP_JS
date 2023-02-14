package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleRepository;
import ru.kata.spring.boot_security.demo.entity.Role;

import java.util.Set;
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    public Set<Role> getRolesByRoleName(String role) {
        String newRole = new String();
        if (role.contains("ROLE_USER") && role.contains("ROLE_ADMIN")) {
            return roleRepository.getAllRoles();
        }
        if (role.contains("ROLE_USER")) {
                newRole = "ROLE_USER";
                return roleRepository.getRolesByRoleName(newRole);
            } else return roleRepository.getRolesByRoleName("ROLE_ADMIN");
    }
    @Override
    public Set<Role> getRolesByUserId(Integer Id) {
        return roleRepository.getRolesByUserId(Id);
    }

    @Override
    @Transactional
    public void saveRole(Role role) {
        roleRepository.saveRole(role);
    }
    @Override
    public Set<Role> getUserRoles() {
        return roleRepository.getAllRoles();
    }
}

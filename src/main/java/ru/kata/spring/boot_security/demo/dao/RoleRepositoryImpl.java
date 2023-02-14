package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entity.Role;
import javax.persistence.EntityManager;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public  class RoleRepositoryImpl implements RoleRepository {
    private  final EntityManager entityManager;
    private final UserRepository userRepository;

    public RoleRepositoryImpl(EntityManager entityManager, UserRepository userRepository) {
        this.entityManager = entityManager;
        this.userRepository = userRepository;
    }
    @Override
    public  void  saveRole(Role role){
        entityManager.persist(role);

    }

    @Override
    public Set<Role> getAllRoles() {
        return entityManager.createQuery("select p from Role p", Role.class).getResultStream().collect(Collectors.toSet());
    }
    @Override
    public Set<Role> getRolesByRoleName(String newRole) {
        return entityManager.createQuery("select p from Role p where p.role =: newRole", Role.class)
                .setParameter("newRole", newRole).getResultStream().collect(Collectors.toSet());
    }

    @Override
    public Set<Role> getRolesByUserId(Integer id) {
        return userRepository.findById(id).get().getRoles();
    }

}

package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;
@Component
public class DbInit {
    private final UserService userService;
    private final RoleService roleService;
    @Autowired
    public DbInit(UserService userService, RoleService roleService) {
        this.userService = userService;

        this.roleService = roleService;
    }

    @PostConstruct
    private void dataBaseInit() {

        Set<Role> adminRole= new HashSet<>();
        Role roleUser = new Role("ROLE_USER");
        Role roleAdmin = new Role ("ROLE_ADMIN");
        adminRole.add(roleAdmin);
        roleService.saveRole(roleAdmin);
        roleService.saveRole(roleUser);
        Set<Role> userRole= new HashSet<>();
        userRole.add(roleUser);


        User newUser = new User("Андрей","Иванов",18, "User","pass","ivanov@com",userRole);
        User newAdmin = new User("Иван","Андреев",54, "Admin","pass","andreev@com",adminRole);

        userService.addUser(newAdmin);
        userService.addUser(newUser);

    }
}


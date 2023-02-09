package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.dao.UserRepository;
import java.util.*;


@Service
public class UserServiceImpl  implements UserService{
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserInfo(int id) {
        return  userRepository.findById(id).get();
    }

    @Override
    public User findUserByUsername(String login) {
        return userRepository.findByUsername(login).get();
    }

    @Override
    @Transactional
    public void addUser(User user){
        user.setPass(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    @Override
    @Transactional
    public void updateUser(User user) {
        if (!user.getPassword().equals(userRepository.findById(user.getId()).get().getPassword())) {
            user.setPass(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
    }
    @Override
    @Transactional
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}


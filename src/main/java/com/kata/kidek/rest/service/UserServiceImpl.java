package com.kata.kidek.rest.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.kata.kidek.rest.model.User;
import com.kata.kidek.rest.repository.RoleRepository;
import com.kata.kidek.rest.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements  UserService{

    private BCryptPasswordEncoder  bCryptPasswordEncoder;
    private UserRepository userRepository;

    public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(Long id) {
       return userRepository.getUserById(id);
    }

    @Override
    public User getUserByEmail(String mail) {
        return userRepository.getUserByEmail(mail);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void edit(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}

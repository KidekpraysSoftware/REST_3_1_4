package com.kata.kidek.rest.service;

import com.kata.kidek.rest.model.User;


import java.util.List;
import java.util.Set;

public interface UserService {
    User getUserById(Long id);

   User getUserByEmail(String mail);
    List<User> getAllUsers();

    void saveUser(User User);
    void edit(User User);

    void deleteUserById(Long id);




}

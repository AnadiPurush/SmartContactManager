package com.utsavsharma.smartContactManager.services;

import java.util.Optional;

import com.utsavsharma.smartContactManager.Entity.User;

/**
 * UserService.java
 * Created by utsav on 03-Aug-2024 at 3:29:40 pm.
 */

public interface UserService {

    /*
     * in this interface we will write a user related method
     * and we will add more method as per the need
     * we will not directly use method to direcltlly intrect with the user data
     * 
     * 
     */

    User saveUser(User user);

    Optional<User> getUserById(String Id);

    Optional<User> updateUser(User user);

    boolean isUserExist(String email);

    User getUserByEmail(String email);

}

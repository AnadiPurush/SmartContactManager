package com.utsavsharma.smartContactManager.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utsavsharma.smartContactManager.Entity.User;


/**
 * UserRepo.java
 * Created by utsav on 03-Aug-2024 at 3:26:51 pm.
 */
@Repository
public interface UserRepo extends JpaRepository<User, String> {
	
 Optional<User>findByEmail(String email);
}



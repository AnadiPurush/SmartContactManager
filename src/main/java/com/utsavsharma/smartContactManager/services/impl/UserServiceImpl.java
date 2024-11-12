package com.utsavsharma.smartContactManager.services.impl;

import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utsavsharma.smartContactManager.Entity.Providers;
import com.utsavsharma.smartContactManager.Entity.User;
import com.utsavsharma.smartContactManager.helper.ResourceNotFoundException;
import com.utsavsharma.smartContactManager.repositories.UserRepo;
import com.utsavsharma.smartContactManager.services.UserService;

/**
 * UserServiceImpl.java
 * Created by utsav on 03-Aug-2024 at 3:40:33 pm.
 */
@Service
public class UserServiceImpl implements UserService {
	private final UserRepo userRepo;
	private final PasswordEncoder passwordEncoder;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	@Transactional
	public User saveUser(User user) {
		String userId = UUID.randomUUID().toString();
		user.setUserId(userId);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setProviders(Providers.SELF);
		user.setEnabled(true);
		logger.info(user.getProviders().toString());

		/*
		 * Password Encoding should be done here;
		 */

		return userRepo.save(user);
	}

	@Override
	@Transactional
	public Optional<User> getUserById(String Id) {
		return userRepo.findById(Id);
	}

	@Override
	@Transactional
	public Optional<User> updateUser(User user) {
		User user1 = userRepo.findById(user.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		user1.setName(user.getName());
		user1.setPassword(user.getPassword());
		user1.setEmail(user.getEmail());
		user1.setPhoneNumber(user.getPhoneNumber());
		user1.setProfilePic(user.getProfilePic());
		user1.isEnabled();
		user1.setEmailVerified(user.isEmailVerified());
		user1.setPhoneVerified(true);

		/*
		 * now save the updatd user to the db
		 */

		User save = userRepo.save(user1);

		return Optional.ofNullable(save);
	}

	@Override
	@Transactional
	public boolean isUserExist(String email) {
		return userRepo.findByEmail(email).isPresent();
	}

	@Override
	@Transactional
	public User getUserByEmail(String email) {

		return userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));
	}

}

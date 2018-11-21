package com.plms.repository;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.plms.entity.User;

public interface UserInfoRepository extends CrudRepository<User, Integer> {
	// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
	// CRUD refers Create, Read, Update, Delete
	Optional<User> findByUsername(String username);

	}

package com.plms.repository;


import org.springframework.data.repository.CrudRepository;

import com.plms.entity.AvailablePolicies;

public interface AvailablePoliciesRepository extends CrudRepository<AvailablePolicies, Integer> {
	// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
	// CRUD refers Create, Read, Update, Delete

	}

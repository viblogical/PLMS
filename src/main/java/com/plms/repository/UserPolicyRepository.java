package com.plms.repository;


import org.springframework.data.repository.CrudRepository;

import com.plms.entity.UserPolicy;

public interface UserPolicyRepository extends CrudRepository<UserPolicy, Integer> {
	// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
	// CRUD refers Create, Read, Update, Delete

	Iterable<UserPolicy> findByUserid(Integer userid);

}

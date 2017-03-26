package org.my.test.repository;

import org.springframework.data.repository.CrudRepository;

import org.my.test.domain.Users;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<Users, Integer> {
    Users findByName(String name);
}

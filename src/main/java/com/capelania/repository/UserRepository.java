package com.capelania.repository;

import com.capelania.model.User;

public interface UserRepository  extends DefaultRepository<User> {

	public User findByEmail(String email);

}
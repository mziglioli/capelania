package com.capelania.repository;

import com.capelania.model.Role;
import java.util.List;

public interface RoleRepository extends DefaultRepository<Role> {

	List<Role> findAllByIdIn(List<Long> ids);
}
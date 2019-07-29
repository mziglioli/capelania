package com.capelania.repository;

import com.capelania.model.EntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface DefaultRepository<T extends EntityJpa>  extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

}
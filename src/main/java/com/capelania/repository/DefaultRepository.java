package com.capelania.repository;

import com.capelania.model.EntityJpa;
import com.capelania.model.Mass;
import com.capelania.model.Status;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface DefaultRepository<T extends EntityJpa>  extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

    List<T> findAllByActive(boolean active);

    default T save(T entity, long userId) {
        if (entity == null) {
            //TODO
        }
        if (entity.getId() != null) {
            return update(entity, userId);
        }
        beforeInsert(entity, userId);
        saveAndFlush(entity);
        afterInsert(entity);
        return entity;
    }

    default T update(T entity, long userId) {
        if (entity == null) {
            //TODO
        }
        beforeUpdate(entity, userId);
        saveAndFlush(entity);
        afterUpdate(entity);
        return entity;
    }

    default T delete(T entity, long userId) {
        if (entity == null) {
            //TODO
        }
        beforeDelete(entity, userId);
        saveAndFlush(entity);
        afterDelete(entity);
        return entity;
    }

    default T delete(Long id, long userId) {
        T entity = getOne(id);
        if(entity == null){
            //TODO
        }
        beforeDelete(entity, userId);
        saveAndFlush(entity);
        afterDelete(entity);
        return entity;
    }

    default void afterInsert(T entity) {

    }

    default void afterUpdate(T entity) {

    }

    default void afterDelete(T entity) {

    }

    default void beforeInsert(T entity, long userId) {
        entity.setStatus(Status.CREATED);
        entity.setCreatedBy(userId);
        entity.setCreatedDate(LocalDateTime.now());
    }

    default void beforeUpdate(T entity, long userId) {
        entity.setStatus(Status.UPDATED);
        entity.setUpdatedBy(userId);
        entity.setUpdatedDate(LocalDateTime.now());
    }

    default void beforeDelete(T entity, long userId) {
        beforeUpdate(entity, userId);
        entity.setActive(false);
    }
}
package com.capelania.service;

import com.capelania.form.DefaultForm;
import com.capelania.model.EntityJpa;
import com.capelania.model.User;
import com.capelania.repository.DefaultRepository;
import java.util.Collection;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

@Log4j
@Getter
public abstract class DefaultService<T extends EntityJpa, R extends DefaultRepository<T>, F extends DefaultForm> {

	protected R repository;

	@Autowired
	public DefaultService(R repository) {
		this.repository = repository;
	}

	protected T addConvert(F form) {
		return (T) form.convertToEntity();
	}
	protected T updateConvert(F form) {
		return (T) form.convertToEntity();
	}

	@Transactional
	public void add(F form) {
		log.info("Save form: " + form.toString());
		T entity = addConvert(form);
		repository.save(entity, getAuthenticatedUserId());
		log.info("Saved entity: " + entity.toString());
	}

	@Transactional
	public void update(F form) {
		log.info("Update form: " + form.toString());
		T entity = updateConvert(form);
		repository.update(entity, getAuthenticatedUserId());
		log.info("Updated entity: " + entity.toString());
	}

	@Transactional
	public void delete(F form) {
		log.info("Delete form: " + form.toString());
		T entity = updateConvert(form);
		repository.delete(entity, getAuthenticatedUserId());
		log.info("Deleted entity: " + entity.toString());
	}

	@Transactional
	public void delete(Long id) {
		log.info("delete by id " + id);
		T entity = repository.delete(id, getAuthenticatedUserId());
	}

	public Collection<T> findAll() {
		return repository.findAll();
	}

	public Page<T> find(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Page<T> find(String search, Pageable pageable) {
		return null;
	}

	public Page<T> find(Specification<T> specification, Pageable pageable) {
		return repository.findAll(specification, pageable);
	}

	public User getAuthenticatedUser() throws AccessDeniedException {
		try {
			SecurityContext context = SecurityContextHolder.getContext();
			if (context != null) {
				Authentication auth = context.getAuthentication();
				if (auth != null && !(auth instanceof AnonymousAuthenticationToken)) {
					return (User) SecurityContextHolder.getContext().getAuthentication().getDetails();
				}
			}
			return null;
		} catch (Exception e) {
			throw new AccessDeniedException("");
		}
	}

	public long getAuthenticatedUserId() {
		User user = getAuthenticatedUser();
		if(user != null){
			return user.getId();
		}
		return 0L;
	}

	public T findById(long id) {
		return repository.findById(id).orElse(null);
	}
}
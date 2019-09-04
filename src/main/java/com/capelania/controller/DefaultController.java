package com.capelania.controller;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.isNumeric;

import com.capelania.form.DefaultForm;
import com.capelania.model.EntityJpa;
import com.capelania.service.DefaultService;
import java.util.List;
import javax.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
public class DefaultController<S extends DefaultService, E extends EntityJpa, F extends DefaultForm> {

    protected S service;

    @Autowired
    public DefaultController(S service) {
        this.service = service;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@Valid @RequestBody F form, BindingResult bindingResult) throws BindException {
        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }
        service.add(form);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@Valid @RequestBody F form, BindingResult bindingResult) throws BindException {
        if(form.getId() == 0) {
            bindingResult.addError(new ObjectError("id", "validator.invalid.id"));
        }
        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }
        service.update(form);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable long id ) {
        if(id == 0) {
            //TODO
        }
        service.delete(id);
    }

    @GetMapping("/all")
    public List<E> findAll() {
        return (List<E>) service.findAll();
    }

    @GetMapping(value = "/filter", params = { "page", "size" })
    public Page<E> findPaginated(@RequestParam("page") String page, @RequestParam("size") String size) {
        int start = 0;
        int qtd = 5;
        if(isNotBlank(page) && isNumeric(page)){
            start = Integer.valueOf(page);
        }
        if(isNotBlank(size) && isNumeric(size)){
            qtd = Integer.valueOf(size);
        }
        return service.find(start, qtd);
    }

    @GetMapping("/{id}")
    public E findById(@PathVariable long id) {
        return (E) service.findById(id);
    }
}
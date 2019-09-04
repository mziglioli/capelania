package com.capelania.service;

import com.capelania.form.UserForm;
import com.capelania.model.Role;
import com.capelania.model.User;
import com.capelania.repository.RoleRepository;
import com.capelania.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends DefaultService<User, UserRepository, UserForm> {

    private RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository repository, RoleRepository roleRepository) {
        super(repository);
        this.roleRepository = roleRepository;
    }

    public List<User> findAll() {
        return getRepository().findAll();
    }

    public User findByUsername(String username) {
        return getRepository().findByEmail(username);
    }

    @Override
    protected User addConvert(UserForm form) {
        User user = super.addConvert(form);
        user.setRoles(getRoles(form.getAllRoles()));
        return user;
    }

    private List<Role> getRoles(String roles) {
        List<String> findNames = Stream.of(roles.split(","))
            .filter(r -> !"ROLE_ADMIN".equals(r))
            .collect(Collectors.toList());
        return roleRepository.findAllByNameIn(findNames);
    }

    @Override
    protected User updateConvert(UserForm form) {
        User user = super.updateConvert(form);
        user.setRoles(getRoles(form.getAllRoles()));
        return user;
    }

    @Override
    public void update(UserForm form) {
        if(form.getId() != 1){
            super.update(form);
        }
    }

    @Override
    public void delete(Long id) {
        if(id != 1){
            super.delete(id);
        }
    }
}

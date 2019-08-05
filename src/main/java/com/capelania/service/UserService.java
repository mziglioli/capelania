package com.capelania.service;

import com.capelania.form.UserForm;
import com.capelania.model.Role;
import com.capelania.model.User;
import com.capelania.repository.RoleRepository;
import com.capelania.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
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
        user.setRoles(getRoles(form.getRoles()));
        return user;
    }

    private List<Role> getRoles(List<Long> ids) {
        // no id = 1 allowed 1 = ADMIN
        List<Long> findIds = ids.stream()
            .filter(r -> r != 1).collect(Collectors.toList());
        return roleRepository.findAllByIdIn(findIds);
    }

    @Override
    protected User updateConvert(UserForm form) {
        User user = super.updateConvert(form);
        user.setRoles(getRoles(form.getRoles()));
        return user;
    }
}

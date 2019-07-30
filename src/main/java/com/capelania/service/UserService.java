package com.capelania.service;

import com.capelania.model.User;
import com.capelania.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByUsername(String username) {
        return userRepository.findByEmail(username);
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
            throw new AccessDeniedException("Error get authenticated user");
        }
    }
}

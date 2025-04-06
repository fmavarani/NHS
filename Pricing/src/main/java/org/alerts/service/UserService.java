package org.alerts.service;

import org.alerts.repository.UserRepository;
import org.alerts.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
    public User createOrUpdate(User user) {
        return userRepository.save(user);
    }
}

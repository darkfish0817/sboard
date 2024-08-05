package com.indielab.demo.sboard.service;

import com.indielab.demo.sboard.model.Role;
import com.indielab.demo.sboard.model.User;
import com.indielab.demo.sboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User save(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setId(1l);                       // form에서 role id drop down list로 선택 후 전달하여 받도록 수정
        user.setPassword(encodedPassword);
        user.setEnabled(true);
        Role role = new Role();
        role.setId(user.getId());
        user.getRoles().add(role);

        return userRepository.save(user);
    }

}

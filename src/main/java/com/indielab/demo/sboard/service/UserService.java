package com.indielab.demo.sboard.service;

import com.indielab.demo.sboard.model.Board;
import com.indielab.demo.sboard.model.Role;
import com.indielab.demo.sboard.model.User;
import com.indielab.demo.sboard.repository.BoardRepository;
import com.indielab.demo.sboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public User save(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setId(1l);                       // form에서 role id drop down list로 선택 후 전달하여 받도록 수정
        user.setPassword(encodedPassword);
        user.setEnabled(true);

        Role role = new Role();
        role.setId(user.getId());
        user.getRoles().add(role);

        User savedUser = userRepository.save(user);

//        if(true) {
//            throw new RuntimeException("강제 예외 발생!");
//        }

        // 가입인사글 자동작성
        Board board = new Board();
        board.setUser(savedUser);
        board.setTitle("안녕하세요");
        board.setContent("반갑습니다.");
        board.setIns_usr(savedUser.getUsername());
        boardRepository.save(board);

        return savedUser;
    }

}

package com.indielab.demo.sboard.service;

import com.indielab.demo.sboard.model.Board;
import com.indielab.demo.sboard.model.User;
import com.indielab.demo.sboard.repository.BoardRepository;
import com.indielab.demo.sboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    public Board save(String username,  Board board) {
        User user = userRepository.findByUsername(username);
        board.setUser(user);
        board.setIns_usr(user.getUsername());
        return boardRepository.save(board);
    }
}

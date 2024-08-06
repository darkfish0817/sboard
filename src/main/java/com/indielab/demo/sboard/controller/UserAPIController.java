package com.indielab.demo.sboard.controller;

import com.indielab.demo.sboard.model.Board;
import com.indielab.demo.sboard.model.User;
//import com.indielab.demo.sboard.repository.BoardRepository;
import com.indielab.demo.sboard.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserAPIController {

    @Autowired
    private UserRepository repository;

//    @Autowired
//    private BoardRepository boardRepository;

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/users")
    List<User> all() {
        List<User> users = repository.findAll();
        log.debug("getBoards().size() 호출전");
        log.debug("getBoards().size() : {}", users.get(0).getBoards().size());
        log.debug("getBoards().size() 호출후");
        return users;
    }
    // end::get-aggregate-root[]

    @PostMapping("/users")
    User newUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    // Single item

    @GetMapping("/users/{id}")
    User one(@PathVariable Long id) {

        return repository.findById(id).orElse(null);
    }

    @PutMapping("/users/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {

        return repository.findById(id)
                .map(user -> {
                    // 전체 다 변경(user repository의 orphanRemoval = true로 변경필요)
//                    user.getBoards().clear();
//                    user.getBoards().addAll(newUser.getBoards());
                    // id로 조회해온 글만 변경
                    user.setBoards(newUser.getBoards());
                    for(Board board : newUser.getBoards()) {

                        board.setUser(user);
                        board.setIns_usr(user.getUsername());
                    }
                    return repository.save(user);
                })
                .orElseGet(() -> {
                    return repository.save(newUser);
                });
    }

//    @PutMapping("/users/{id}")
//    User replaceUser(@RequestBody Board newBoard, @PathVariable Long id) {
//
//        return boardRepository.findById(id)
//                .map(board -> {
////                    user.setTitle(newUser.getTitle());
////                    user.setContent(newUser.getContent());
//                    user.setBoards(newUser.getBoards());
//                    for(Board board : newUser.getBoards()) {
//                        board.setUser(user);
//                    }
//                    return boardRepository.save(user);
//                })
//                .orElseGet(() -> {
//                    return boardRepository.save(newUser);
//                });
//    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}

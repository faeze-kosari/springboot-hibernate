package com.rajman.demo;


import com.rajman.demo.data.Post;
import com.rajman.demo.data.Users;
//import com.rajman.demo.data.UsersVO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserHandler implements CommandLineRunner {

    private UserRepository userRepository;
    private PostRepository postRepository;

    public UserHandler(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }


    Users add(Users user) {
        user.getPosts().forEach(post -> {
            post.setUser(user);
        });
        user.getUserRoles().forEach(userRole -> {
            userRole.setUser(user);
        });
        return userRepository.save(user);
    }

    String delete(long id) {
        Optional<Users> ui = userRepository.findById(id);
        if (ui.isPresent()) {
            userRepository.delete(ui.get());
            return "user with id" + id + "deleted";
        } else
            return "user not found";
    }

    String getById(long id) {
        Optional<Users> ui = userRepository.findById(id);
        if (ui.isPresent()) {
            return ui.get().getUsername();
        } else
            return "user not found";
    }

    String update_username(Users user) {
        Optional<Users> ui = userRepository.findById(user.getId());
        if (ui.isPresent()) {
            ui.get().setUsername(user.getUsername());
            userRepository.save(ui.get());
            return ui.get().getUsername();
        } else
            return "user not found";
    }


    @Override
    public void run(String... args) throws Exception {

    }
}

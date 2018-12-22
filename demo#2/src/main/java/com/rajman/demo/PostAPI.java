package com.rajman.demo;

import com.rajman.demo.data.Post;
import com.rajman.demo.data.Users;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostAPI {
    private PostRepository postRepository;
    private UserRepository userRepository;

    public PostAPI(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/add/{id}", produces = "application/json", consumes = "application/json")
    public String addToUser(@PathVariable("id") long id,
                            @RequestBody Post post) {
        Optional<Users> ui = userRepository.findById(id);
        if (ui.isPresent()) {
            List<Post> list = new ArrayList<>();
            list.add(post);
            post.setUser(ui.get());
            ui.get().setPosts(list);
            postRepository.save(post);
            return "post with name " + post.getName() + " added to user with username " + ui.get().getUsername();
        } else
            return "user not found";

    }

}

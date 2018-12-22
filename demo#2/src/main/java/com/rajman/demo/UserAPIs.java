package com.rajman.demo;

import com.rajman.demo.data.Users;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserAPIs {
    private UserHandler userHandler;

    public UserAPIs(UserHandler userHandler) {
        this.userHandler = userHandler;
    }


    @PostMapping(value = "/adduser"
            , produces = "application/json", consumes = "application/json")
    public Users add_user(@RequestBody Users user) {
        return userHandler.add(user);
    }

    @DeleteMapping("/deleteuser")
    public String delete_user(@RequestParam("id") long id) {
        return userHandler.delete(id);
    }

    @GetMapping(value = "/getbyid")
    public String get_user_by_id(@RequestParam("id") long id) {
        return userHandler.getById(id);
    }

    @PutMapping(value = "/updateuser")
    public String update_user(@RequestBody Users user) {
        return userHandler.update_username(user);
    }
}

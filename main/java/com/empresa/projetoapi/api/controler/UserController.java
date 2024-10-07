package com.empresa.projetoapi.api.controler;

import com.empresa.projetoapi.model.User;
import com.empresa.projetoapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value="/user", produces = {"application/json", "application/xml"})
    public User getUser(@RequestParam Integer id){
        Optional<User> user = userService.getUser(id);
        return (User)user.orElse(null);
    }

    @GetMapping(value="/users",produces={"application/json", "application/xml"})
    public List<User> getAllUsers() {
        return userService.getUserList();
    }

    @PostMapping(value="/user", consumes = {"application/json", "application/xml"})
    public ResponseEntity<String> createUser(@RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("ok");
    }

    @DeleteMapping(value="/user", consumes = {"application/json", "application/xml"})
    public ResponseEntity<String> deleteUser(@RequestParam Integer id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return ResponseEntity.ok("User deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @PutMapping(value="/user", consumes = {"application/json", "application/xml"})
    public ResponseEntity<String> updateUser (@RequestParam Integer Id, @RequestBody User editUser){
        Optional<User> user = userService.updateUser(Id, editUser);
        if(user.isPresent()){
            return ResponseEntity.ok("User updated successfully");
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

    }
}

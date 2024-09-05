package cursospringboot.example.cursospringbootjava.controllers;

import cursospringboot.example.cursospringbootjava.repositories.UserReposetery;
import cursospringboot.example.cursospringbootjava.services.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cursospringboot.example.cursospringbootjava.models.User;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    @PostMapping
    public ResponseEntity<User>CreateUserController(@RequestBody User user){
        User newuser = this.userService.create(user);
        return new ResponseEntity<>(newuser, HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>>GetAllUsers(User user){
        List<User> getAllUsers = this.userService.getAllUsers();
        return new ResponseEntity<>(getAllUsers, HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public void DeleteUser(@PathVariable int id){
        this.userService.delete(id);
    }
}

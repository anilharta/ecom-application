package com.app.ecom;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }


//    @GetMapping("/api/users")
//    public List<User> getAllUsers() {
//        return userService.fetchAllUsers();
//    }

  //  @GetMapping("/api/users")
    @GetMapping
  //  @RequestMapping(value = "/api/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.fetchAllUsers(), HttpStatus.CREATED);
        //ResponseEntity.ok(userService.fetchAllUsers());
        //return userService.fetchAllUsers();
    }

//    @GetMapping("/api/users/{id}")
//    public User getUser(@PathVariable long id) {
//          return userService.fetchUser(id);
//    }

//    @GetMapping("/api/users/{id}")
//    public ResponseEntity<User> getUser(@PathVariable long id) {
//        User user = userService.fetchUser(id);
//        if(user == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(user);
//    }

    //@GetMapping("/api/users/{id}")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        return userService.fetchUser(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

//    @PostMapping("/api/users")
//    public String createUser(@RequestBody User user) {
//        userService.addUser(user);
//        return "User added successfully";
//    }

    //@PostMapping("/api/users")
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok("User added successfully");
    }

    // put mapping
//    @PutMapping("/api/users/{id}")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable long id, @RequestBody User udpatedUser) {
        boolean updatedUser = userService.updateUser(id, udpatedUser);
        if (updatedUser) {
            return ResponseEntity.ok("User updated successfully");
        }
        return ResponseEntity.notFound().build();
    }
}
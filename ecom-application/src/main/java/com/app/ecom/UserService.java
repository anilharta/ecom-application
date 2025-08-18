package com.app.ecom;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
 //replacing with repository
 // private List<User> userList = new ArrayList<>();
  //  private long id= 1L;

//    public List<User> fetchAllUsers() {
//        return userList;
//    }

    public List<User> fetchAllUsers() {
        return userRepository.findAll();
    }

//    public List<User> addUser(User user) {
//        System.out.println("Setting ID with value: " + id);
//        user.setId(id++);
//        userList.add(user);
//        return userList;
//    }

    public void addUser(User user) {
       userRepository.save(user);
    }

//    public User fetchUser(long id) {
//        for(User user: userList) {
//            if(user.getId().equals(id)) {
//                return user;
//            }
//        }
//        return null;
//    }

    // Use stream API instead of for :
//    public Optional<User> fetchUser(long id) {
//        return userList.stream()
//                .filter(user -> user.getId().equals(id))
//                .findFirst();
//    }

    public Optional<User> fetchUser(long id) {
        return userRepository.findById(id);
    }

    public boolean updateUser(long id, User udpatedUser) {

        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setLastName(udpatedUser.getLastName());
                    existingUser.setFirstName(udpatedUser.getFirstName());
                    userRepository.save(existingUser);
                    return true;
                }).orElse(false);
    }

//    public boolean updateUser(long id, User udpatedUser) {
//
//        return userList.stream()
//                        .filter(user -> user.getId().equals(id))
//                        .findFirst()
//                        .map(existingUser -> {
//                            existingUser.setLastName(udpatedUser.getLastName());
//                            existingUser.setFirstName(udpatedUser.getFirstName());
//                            return true;
//                        }).orElse(false);
//    }
}

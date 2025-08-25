package com.ecommerce.User.service;

import com.ecommerce.User.dto.AddressDTO;
import com.ecommerce.User.dto.UserRequest;
import com.ecommerce.User.dto.UserResponse;
import com.ecommerce.User.model.Address;
import com.ecommerce.User.model.User;
import com.ecommerce.User.repositories.UserRepository;
import com.ecommerce.User.dto.AddressDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserResponse> fetchAllUsers() {
        List<User> userList = userRepository.findAll();
        return userRepository.findAll().stream()
                .map(this::mapToUserResponce)
                .collect(Collectors.toList());
    }

    public void addUser(UserRequest userRequest) {
       User user = new User();
       updateUserFromRequest(user, userRequest);
        userRepository.save(user);
    }


    public Optional<UserResponse> fetchUser(long id) {
        return userRepository.findById(id)
                .map(this::mapToUserResponce);
    }

    public boolean updateUser(long id, UserRequest udpatedUserRequest) {

        return userRepository.findById(id)
                .map(existingUser -> {
//                    existingUser.setLastName(udpatedUser.getLastName());
//                    existingUser.setFirstName(udpatedUser.getFirstName());
                    updateUserFromRequest(existingUser, udpatedUserRequest);
                    userRepository.save(existingUser);
                    return true;
                }).orElse(false);
    }


    private void updateUserFromRequest(User user, UserRequest userRequest) {
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());

        if(userRequest.getAddress() != null) {
            Address address = new Address();
            address.setStreet(userRequest.getAddress().getStreet());
            address.setCity(userRequest.getAddress().getCity());
            address.setState(userRequest.getAddress().getState());
            address.setCountry(userRequest.getAddress().getCountry());
            address.setZipcode(userRequest.getAddress().getZipcode());
            user.setAddress(address);
          }
    }



    private UserResponse mapToUserResponce(User user) {
        UserResponse response = new UserResponse();
        response.setId(String.valueOf(user.getId()));
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setRole(user.getRole());

        if(user.getAddress() != null) {
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setCity(user.getAddress().getCity());
            addressDTO.setStreet(user.getAddress().getStreet());
            addressDTO.setState(user.getAddress().getState());
            addressDTO.setCountry(user.getAddress().getCountry());
            addressDTO.setZipcode(user.getAddress().getZipcode());
            response.setAddress(addressDTO);
        }
        return response;
    }
}

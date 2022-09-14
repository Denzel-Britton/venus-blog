package com.example.venusblog.controller;

import com.example.venusblog.data.User;
import com.example.venusblog.repository.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/api/users", produces = "application/json")
public class UsersController {


    private UsersRepository usersRepository;

    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping("")
    public List<User> fetchUsers() {
        return usersRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> fetchUserById(@PathVariable long id) {
      return  usersRepository.findById(id);
    }



    //work on the me
    @GetMapping("/me")
        private Optional<User> fetchMe(){
        return usersRepository.findById(1L);
    }





//    @GetMapping("/username/{userName}")
//    private User fetchByUserName(@PathVariable String userName) {
//        User user = findUserByUserName(userName);
//        if(user == null) {
//            // what to do if we don't find it
//            throw new RuntimeException("I don't know what I am doing");
//        }
//        return user;
//    }


//    @GetMapping("/email/{email}")
//    private User fetchByEmail(@PathVariable String email) {
//        User user = findUserByEmail(email);
//        if(user == null) {
//            // what to do if we don't find it
//            throw new RuntimeException("I don't know what I am doing");
//        }
//        return user;
//    }



//    private User findUserByEmail(String email) {
//        for (User user: users) {
//            if(user.getEmail().equals(email)) {
//                return user;
//            }
//        }
//        // didn't find it so do something
//        return null;
//    }

//    private User findUserById(long id) {
//        for (User user: users) {
//            if(user.getId() == id) {
//                return user;
//            }
//        }
//        // didn't find it so do something
//        return null;
//    }

    @PostMapping("/create")
    public void createUser(@RequestBody User newUser) {
        // assign  nextId to the new post
       usersRepository.save(newUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable long id) {
    usersRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public void updateUser(@RequestBody User updatedUser, @PathVariable long id) {
        // find the post to update in the posts list
            updatedUser.setId(id);
            usersRepository.save(updatedUser);
//
//        User user = findUserById(id);
//        if(user == null) {
//            System.out.println("User not found");
//        } else {
//            if(updatedUser.getEmail() != null) {
//                user.setEmail(updatedUser.getEmail());
//            }
//            return;
//        }
//        throw new RuntimeException("User not found");
    }

    @PutMapping("/{id}/updatePassword")
    private void updatePassword(@PathVariable Long id, @RequestParam(required = false) String oldPassword, @Valid @Size(min = 3) @RequestParam String newPassword) {
        User user = usersRepository.findById(id).get();
//        if(user == null) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"cannot find user " + id);
//        }

        // compare old password with saved pw
        if(!user.getPassword().equals(oldPassword)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "amscray");
        }

        // validate new password
        if(newPassword.length() < 3) {
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST,"new pw length must be at least 3");
        }

        user.setPassword(newPassword);
        usersRepository.save(user);
    }
}
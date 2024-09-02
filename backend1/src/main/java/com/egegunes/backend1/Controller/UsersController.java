package com.egegunes.backend1.Controller;


import com.egegunes.backend1.Entity.Users;
import com.egegunes.backend1.Repository.IUsersRepo;
import com.egegunes.backend1.Service.Interface.IUsersService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UsersController {

    private final IUsersService usersService;

    @PostMapping("/save")
    ResponseEntity<Users> createUser(@RequestBody Users user){
        return ResponseEntity.ok(usersService.save(user));
    }




}

package edu.ucsb.cs156.mobility.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.ucsb.cs156.mobility.entities.User;
import edu.ucsb.cs156.mobility.repositories.UserRepository;

import edu.ucsb.cs156.mobility.errors.EntityNotFoundException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;


@Tag(name = "User information (admin only)")
@RequestMapping("/api/admin/users")
@RestController
public class UsersController extends ApiController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ObjectMapper mapper;

    @Operation(summary = "Get a list of all users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("")
    public ResponseEntity<String> users()
            throws JsonProcessingException {
        Iterable<User> users = userRepository.findAll();
        String body = mapper.writeValueAsString(users);
        return ResponseEntity.ok().body(body);
    }

    @Operation(summary = "Get user by id")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/get")
    public User users(
            @Parameter(name = "id", description = "Long, id number of user to get", example = "1", required = true) @RequestParam Long id)
            throws JsonProcessingException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(User.class, id));
        return user;
    }

    @Operation(summary = "Delete a user (admin)")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete")
    public Object deleteUser_Admin(
            @Parameter(name = "id", description = "Long, id number of user to delete", example = "1", required = true) @RequestParam Long id) {
              User user = userRepository.findById(id)
          .orElseThrow(() -> new EntityNotFoundException(User.class, id));

          userRepository.delete(user);

        return genericMessage("User with id %s deleted".formatted(id));
    }

    
    @Operation(summary = "Toggle the admin field")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/toggleAdmin")
    public Object toggleAdmin( @Parameter(name = "id", description = "Long, id number of user to toggle their admin field", example = "1", required = true) @RequestParam Long id){
        User user = userRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(User.class, id));

        user.setAdmin(!user.getAdmin());
        userRepository.save(user);
        return genericMessage("User with id %s has toggled admin status to %s".formatted(id, user.getAdmin()));
    }


    @Operation(summary = "Toggle the driver field")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/toggleDriver")
    public Object toggleDriver( @Parameter(name = "id", description = "Long, id number of user to toggle their driver field", example = "1", required = true) @RequestParam Long id){

        User user = userRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(User.class, id));

        user.setDriver(!user.getDriver());
        userRepository.save(user);
        return genericMessage("User with id %s has toggled driver status to %s".formatted(id, user.getDriver()));
    }

    @Operation(summary = "Toggle the rider field")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/toggleRider")
    public Object toggleRider( @Parameter(name = "id") @RequestParam Long id){
        User user = userRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(User.class, id));

        user.setRider(!user.getRider());
        userRepository.save(user);
        return genericMessage("User with id %s has toggled rider status to %s".formatted(id, user.getRider()));
    }
}
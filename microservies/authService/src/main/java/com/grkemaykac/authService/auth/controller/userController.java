package com.grkemaykac.authService.auth.controller;

import com.grkemaykac.authService.auth.dto.userDto;
import com.grkemaykac.authService.auth.dto.userLoginDto;
import com.grkemaykac.authService.auth.service.userService;
import com.grkemaykac.authService.config.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class userController {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    userService UserService;

    @PostMapping("/register")
    public ResponseEntity<userDto> register(@RequestBody @Valid userDto newUser)
    {
        return ResponseEntity.ok(UserService.saveUser(newUser));
    }

    @PutMapping("/deleteUser")
    public ResponseEntity<userDto> delete(@RequestBody @Valid userDto deletedUser)
    {
        return ResponseEntity.ok(UserService.deleteUser(deletedUser));
    }

    @PutMapping("/activeUser")
    public ResponseEntity<userDto> active(@RequestBody @Valid userDto activeUser)
    {
        return ResponseEntity.ok(UserService.activeUser(activeUser));
    }

    @PutMapping("/updateUser")
    public ResponseEntity<userDto> update(@RequestBody @Valid userDto newUser)
    {
        return ResponseEntity.ok(UserService.editUser(newUser));
    }

    @GetMapping("/getUser")
    public ResponseEntity<userDto> get(@RequestBody userDto getUser)
    {
        return ResponseEntity.ok(UserService.getFullUser(getUser));
    }

    @PostMapping("/login")
    public String creteToken(@RequestBody userLoginDto UserLoginDto) throws Exception {
        try
        {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(UserLoginDto.getUsername(), UserLoginDto.getPassword()));
        }
        catch (BadCredentialsException ex)
        {
            throw new Exception("Incorrect username or password", ex);
        }
        final UserDetails userDetails = UserService.loadUserByUsername(UserLoginDto.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return jwt;
    }
}

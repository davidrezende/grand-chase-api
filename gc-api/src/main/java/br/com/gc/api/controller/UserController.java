package br.com.gc.api.controller;

import br.com.gc.api.model.User;
import br.com.gc.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserRepository userRepository;

    @GetMapping(path = "/find/login/{login}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> getUserInfo(@PathVariable String login)
            throws Exception {
        log.info("Iniciando a busca");
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new Exception("Login not found for this login :: " + login));
        return ResponseEntity.ok().body(user);
    }

    @PostMapping(path = "/save", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> newUser(@RequestBody User user)
            throws Exception {

        log.info("Saving new user:");
        user.setIpAddress("127.0.0.1");
        user.setFirstLogin(new Date());
        user.setLastConnect(new Date());
        user.setLastLogin(new Date());
        log.info(user.toString());

        return ResponseEntity.ok().body(userRepository.save(user));
    }

    @PutMapping(path = "/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> updateUser(@RequestBody User user)
            throws Exception {
        log.info("Updating user:");
        log.info(user.toString());
        return ResponseEntity.ok().body(userRepository.save(user));
    }

    @DeleteMapping(path = "/delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void deleteUser(@RequestBody User user)
            throws Exception {
        log.info("Excluding user:");
        log.info(user.toString());

        userRepository.findByLoginUID(user.getLoginUID())
        .orElseThrow(() -> new Exception("User not found for this loginUID:: " + user.getLoginUID()));

        userRepository.delete(user);
    }
}

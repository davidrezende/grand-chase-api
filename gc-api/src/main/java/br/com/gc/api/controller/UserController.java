package br.com.gc.api.controller;

import br.com.gc.api.constants.GlobalConstants;
import br.com.gc.api.model.User;
import br.com.gc.api.repository.UserRepository;
import br.com.gc.api.util.DateFormatSQLServer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Slf4j
@CrossOrigin
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
        user.setFirstLogin(DateFormatSQLServer.format(new Date(), GlobalConstants.DATE_TIME_FORMAT));
        user.setLastConnect(DateFormatSQLServer.format(new Date(), GlobalConstants.DATE_TIME_FORMAT));
        user.setLastLogin(DateFormatSQLServer.format(new Date(), GlobalConstants.DATE_TIME_FORMAT));
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

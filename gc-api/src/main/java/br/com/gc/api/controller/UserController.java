package br.com.gc.api.controller;

import br.com.gc.api.constants.GlobalConstants;
import br.com.gc.api.error.ErrorResponse;
import br.com.gc.api.error.RestExceptionHandler;
import br.com.gc.api.model.User;
import br.com.gc.api.repository.UserRepository;
import br.com.gc.api.util.DateFormatSQLServer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserRepository userRepository;

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MOD') and #oauth2.hasScope('read')")
    @GetMapping(path = "/find/login/{login}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getUserInfo(@PathVariable String login) {
        log.info("Searching login " + login);
        try{
            User user = userRepository.findByLogin(login)
                    .orElseThrow(() -> new Exception("Login not found for this login :: " + login));
            return ResponseEntity.ok().body(user);
        } catch (DataIntegrityViolationException e) {
            ErrorResponse errorResponse = RestExceptionHandler.getConstraintErrors(e);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            ErrorResponse errorResponse = RestExceptionHandler.getExceptionsErrors(e);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') and #oauth2.hasScope('write')")
    @PostMapping(path = "/save", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> newUser(@RequestBody @Valid User user) {
        log.info("Saving new user:");
        try {
            user.setIpAddress("127.0.0.1");
            user.setFirstLogin(DateFormatSQLServer.format(new Date(), GlobalConstants.DATE_TIME_FORMAT));
            user.setLastConnect(DateFormatSQLServer.format(new Date(), GlobalConstants.DATE_TIME_FORMAT));
            user.setLastLogin(DateFormatSQLServer.format(new Date(), GlobalConstants.DATE_TIME_FORMAT));
            log.info(user.toString());
            return ResponseEntity.ok().body(userRepository.save(user));
        } catch (DataIntegrityViolationException e) {
            ErrorResponse errorResponse = RestExceptionHandler.getConstraintErrors(e);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            ErrorResponse errorResponse = RestExceptionHandler.getExceptionsErrors(e);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') and #oauth2.hasScope('write')")
    @PutMapping(path = "/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        log.info("Updating user:");
        log.info(user.toString());
        try {
            return ResponseEntity.ok().body(userRepository.save(user));
        } catch (DataIntegrityViolationException e) {
            ErrorResponse errorResponse = RestExceptionHandler.getConstraintErrors(e);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            ErrorResponse errorResponse = RestExceptionHandler.getExceptionsErrors(e);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') and #oauth2.hasScope('write')")
    @DeleteMapping(path = "/delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void deleteUser(@RequestBody User user) throws Exception {
        log.info("Excluding user:");
        log.info(user.toString());
            userRepository.findByLoginUID(user.getLoginUID())
                    .orElseThrow(() -> new Exception("User not found for this loginUID:: " + user.getLoginUID()));
            userRepository.delete(user);
    }
}

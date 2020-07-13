package br.com.gcpanel.controller;

import br.com.gcpanel.model.User;
import br.com.gcpanel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserRepository userRepository;

    @GetMapping(path = "/login/{login}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> getEmployeeById(@PathVariable String login)
            throws Exception {
        log.info("Iniciando a busca");
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new Exception("Login not found for this login :: " + login));
        return ResponseEntity.ok().body(user);
    }
}

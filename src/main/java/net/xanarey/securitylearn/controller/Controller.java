package net.xanarey.securitylearn.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('dev:admin')")
    public ResponseEntity<String> getAdmin() {
        return new ResponseEntity<>("ADMIN", HttpStatus.OK);
    }

    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('dev:user')")
    public ResponseEntity<String> getUser() {
        return new ResponseEntity<>("USER", HttpStatus.OK);
    }

}

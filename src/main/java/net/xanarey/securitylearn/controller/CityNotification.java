package net.xanarey.securitylearn.controller;

import net.xanarey.securitylearn.service.impl.CityNotificationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityNotification {

    private final CityNotificationImpl cityNotification;

    @Autowired
    public CityNotification(CityNotificationImpl cityNotification) {
        this.cityNotification = cityNotification;
    }

    @GetMapping(value = "/city", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('dev:admin','dev:user')")
    public ResponseEntity<List<net.xanarey.securitylearn.model.CityNotification>> getAdmin() {
        return new ResponseEntity<>(cityNotification.getAllCity(), HttpStatus.OK);
    }
}

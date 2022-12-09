package com.works.restcontrollers;

import com.works.profiles.IConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProfileRestController {

    final IConfig iConfig;

    @GetMapping("/config")
    public ResponseEntity config() {
        return new ResponseEntity(iConfig.config(), HttpStatus.OK);
    }

}

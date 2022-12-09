package com.works.restcontrollers;

import com.works.profiles.IConfig;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProfileRestController {

    private Logger logger = LoggerFactory.getLogger(ProfileRestController.class);
    final IConfig iConfig;

    @GetMapping("/config")
    public ResponseEntity config() {
        logger.info("info message");
        logger.error("error message");
        return new ResponseEntity(iConfig.config(), HttpStatus.OK);
    }

}

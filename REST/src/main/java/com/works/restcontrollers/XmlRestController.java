package com.works.restcontrollers;

import com.works.services.XmlService;
import com.works.utils.ERest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class XmlRestController {

    final XmlService xmlService;

    @GetMapping("/xml")
    public ResponseEntity xml() {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status, true);
        hm.put(ERest.result, xmlService.xml() );
        return new ResponseEntity(hm, HttpStatus.OK);
    }

}

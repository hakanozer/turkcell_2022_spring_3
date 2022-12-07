package com.works.restcontrollers;

import com.works.utils.ERest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CustomerRestController {

    @GetMapping("/")
    public Map customer() {
        Map<ERest, Object> hm = new HashMap<>();
        hm.put(ERest.status, true);
        hm.put(ERest.message, "Customer Success");
        return hm;
    }


}

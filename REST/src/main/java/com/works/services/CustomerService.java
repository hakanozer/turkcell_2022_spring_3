package com.works.services;

import com.works.entities.Customer;
import com.works.repositories.CustomerRepository;
import com.works.utils.ERest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    final CustomerRepository customerRepository;

    public ResponseEntity save(Customer customer) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        Optional<Customer> optionalCustomer = customerRepository.findByEmailEqualsIgnoreCase(customer.getEmail());
        if (optionalCustomer.isPresent()) {
            hm.put(ERest.status, false);
            hm.put(ERest.message, "Email Fail");
            hm.put(ERest.result, customer);
            return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
        }else {
            customerRepository.save(customer);
            hm.put(ERest.status, true);
            hm.put(ERest.result, customer);
            return new ResponseEntity(hm, HttpStatus.OK);
        }
    }

}

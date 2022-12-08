package com.works.services;

import com.works.entities.Admin;
import com.works.repositories.AdminRepository;
import com.works.utils.ERest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminDetailService implements UserDetailsService {

    final AdminRepository adminRepository;
    final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public ResponseEntity register(Admin admin) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        Optional<Admin> optionalAdmin = adminRepository.findByUsernameEqualsIgnoreCase(admin.getUsername());
        if (optionalAdmin.isPresent()) {
            hm.put(ERest.status, false);
            hm.put(ERest.message, "UserName Fail");
            return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
        }else {
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            adminRepository.save(admin);
            hm.put(ERest.status, true);
            hm.put(ERest.result, admin);
            return new ResponseEntity(hm, HttpStatus.OK);
        }
    }

}

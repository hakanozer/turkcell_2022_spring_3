package com.works.services;

import com.works.entities.Admin;
import com.works.entities.Role;
import com.works.repositories.AdminRepository;
import com.works.utils.ERest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminDetailService implements UserDetailsService {

    final AdminRepository adminRepository;
    final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> optionalAdmin = adminRepository.findByUsernameEqualsIgnoreCase(username);
        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            return new User(
                    admin.getUsername(),
                    admin.getPassword(),
                    admin.getEnable(),
                    true,
                    true,
                    true,
                    parseRoles(admin.getRoles())
            );
        }else {
            throw new UsernameNotFoundException("UserName Not Found");
        }
    }

    private Collection<? extends GrantedAuthority> parseRoles(List<Role> roles) {
        List<GrantedAuthority> ls = new ArrayList<>();
        for(Role role : roles) {
            ls.add(new SimpleGrantedAuthority(role.getName()));
        }
        return ls;
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

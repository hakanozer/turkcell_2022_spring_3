package com.works.services;

import com.works.entities.Admin;
import com.works.repositories.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    final AdminRepository adminRepository;
    final TinkEncDec tinkEncDec;

    public Admin save(Admin admin) {
        try {
            admin.setPassword(tinkEncDec.encrypt(admin.getPassword()));
            adminRepository.save(admin);
            return admin;
        }catch (Exception ex) {
            return null;
        }
    }

    public Admin login(Admin admin) {
        Optional<Admin> optionalAdmin = adminRepository.findByEmailEqualsIgnoreCase(admin.getEmail());
        if ( optionalAdmin.isPresent() ) {
            Admin adm = optionalAdmin.get();
            String dbPass = tinkEncDec.decrypt(adm.getPassword());
            if (dbPass.equals(admin.getPassword())) {
                return adm;
            }
        }
        return null;
    }


}

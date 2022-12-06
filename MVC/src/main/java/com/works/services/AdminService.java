package com.works.services;

import com.works.entities.Admin;
import com.works.repositories.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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


}

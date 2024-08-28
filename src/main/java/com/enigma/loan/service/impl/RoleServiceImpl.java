package com.enigma.loan.service.impl;

import com.enigma.loan.entity.Role;
import com.enigma.loan.repository.RoleRepository;
import com.enigma.loan.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getOrSave(Role.ERole role) {
        Optional<Role> optionalRole = roleRepository.findByRoles(role);
        // role available return it
        if (optionalRole.isPresent()){
            return optionalRole.get();
        }

        // role not available create new
        Role currentRole = Role.builder()
                .roles(role)
                .build();

        return roleRepository.saveAndFlush(currentRole);
    }
}

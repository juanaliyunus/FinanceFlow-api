package com.enigma.loan.service;

import com.enigma.loan.entity.Role;

public interface RoleService {
    Role getOrSave (Role.ERole role);
}

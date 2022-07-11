package com.prominentpixel.springbootsecurity.dao;

import com.prominentpixel.springbootsecurity.entity.Role;

public interface RoleDAO {

    Role findRoleByName(String name);

}

package com.prominentpixel.springbootsecurity.service;

import com.prominentpixel.springbootsecurity.dao.RoleDAO;
import com.prominentpixel.springbootsecurity.dao.UserDAO;
import com.prominentpixel.springbootsecurity.entity.Role;
import com.prominentpixel.springbootsecurity.entity.User;
import com.prominentpixel.springbootsecurity.user.CRMUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User findByUserName(String userName) {
        return this.userDAO.findByUserName(userName);
    }

    @Override
    @Transactional
    public void save(CRMUser userOfCRM) {
        User user = new User();
        user.setUserName(userOfCRM.getUserName());
        user.setPassword(this.passwordEncoder.encode(userOfCRM.getPassword()));
        user.setFirstName(userOfCRM.getFirstName());
        user.setLastName(userOfCRM.getLastName());
        user.setEmail(userOfCRM.getEmail());

        user.setRoles(Arrays.asList(this.roleDAO.findRoleByName("ROLE_EMPLOYEE")));
        this.userDAO.save(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = this.userDAO.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password!");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), this.mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}

package com.prominentpixel.springbootsecurity.repository;

import com.prominentpixel.springbootsecurity.entity.LoginLogoutHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginLogoutRepository extends JpaRepository<LoginLogoutHistory, Integer> {

}

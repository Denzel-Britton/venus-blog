package com.example.venusblog.repository;

import com.example.venusblog.data.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsersRepository extends  JpaRepository<User, Long> {
    User findByUserName(String unserName);
    User findByEmail(String email);

}

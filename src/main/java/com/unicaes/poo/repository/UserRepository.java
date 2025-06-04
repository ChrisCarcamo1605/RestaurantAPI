package com.unicaes.poo.repository;

import com.unicaes.poo.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByActiveIsTrue();
}

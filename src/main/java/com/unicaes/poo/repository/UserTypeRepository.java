package com.unicaes.poo.repository;

import com.unicaes.poo.domain.user.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTypeRepository extends JpaRepository<UserType, Long> {
}

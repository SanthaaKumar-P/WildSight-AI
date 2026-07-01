package com.wildsight.backend.repository;

import com.wildsight.backend.entity.User;
import com.wildsight.backend.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    List<UserRole> findByUser(User user);

}
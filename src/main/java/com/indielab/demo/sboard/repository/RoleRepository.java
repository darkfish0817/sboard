package com.indielab.demo.sboard.repository;

import com.indielab.demo.sboard.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}

package com.swp391.evproject.dao;

import com.swp391.evproject.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findByName(String name);
}

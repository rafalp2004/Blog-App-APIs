package com.blog_rest_app.repository;

import com.blog_rest_app.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("SELECT r From Role r WHERE r.name = ?1 ")
    Role findByName(String name);

}

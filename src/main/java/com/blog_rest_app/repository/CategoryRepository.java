package com.blog_rest_app.repository;

import com.blog_rest_app.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("select  c from Category c where c.name=?1")
    Category findByName(String name);
}

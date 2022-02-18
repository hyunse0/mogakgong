package com.ssafy.mogakgong.repository;

import com.ssafy.mogakgong.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findFirstByName(String name);
}

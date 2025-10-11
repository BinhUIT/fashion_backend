package com.example.fashion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fashion.domain.entity.mysqltables.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}

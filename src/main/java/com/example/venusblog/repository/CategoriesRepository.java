package com.example.venusblog.repository;

import com.example.venusblog.data.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Category ,Long> {

}
package com.company.springboot.repository;

import com.company.springboot.domain.Food;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FoodRepo extends CrudRepository<Food, Long> {

    List<Food> findByTag(String tag);

}
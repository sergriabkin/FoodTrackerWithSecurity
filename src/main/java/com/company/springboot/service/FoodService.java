package com.company.springboot.service;

import com.company.springboot.domain.Food;

public interface FoodService {

    Iterable<Food> findAll();

    Food save(Food food);

    Iterable<Food> findByTag(String filter);
}

package com.company.springboot.service;

import com.company.springboot.domain.Food;
import com.company.springboot.domain.User;

public interface FoodService {

    Iterable<Food> findAll();

    Food save(Food food);

    Iterable<Food> findByTag(String filter);

    int caloriesCount(User user);
}

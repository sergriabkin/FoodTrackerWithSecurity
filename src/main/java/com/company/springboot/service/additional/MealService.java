package com.company.springboot.service.additional;

import com.company.springboot.entity.Dish;
import com.company.springboot.entity.Meal;

import java.time.LocalDate;
import java.util.List;

public interface MealService {

    List<Meal> getAll();

    Meal save(String description, Integer calories, Long userId);

    Meal getMeal(Long id);

    Meal updateMeal(Long id, String description, Integer calories, Long userId);

    void deleteMeal(Long id);

    List<Meal> findAllByDate(LocalDate localDate, Long userId);

    String getMessageWithExcess(String date, Long userId);

    List<Dish> findAllDishes();

    Meal createMealFromDishes(String dishes, Long userId);

    Dish findDishById(Long id);

    Dish saveDish(String name, Integer calories);
}

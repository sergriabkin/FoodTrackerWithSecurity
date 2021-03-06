package com.company.springboot.service;

import com.company.springboot.domain.Food;
import com.company.springboot.domain.User;
import com.company.springboot.repository.FoodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodServiceImpl implements FoodService {

    private FoodRepo repo;

    @Autowired
    public FoodServiceImpl(FoodRepo repo) {
        this.repo = repo;
    }

    @Override
    public Iterable<Food> findAll() {
        return repo.findAll();
    }

    @Override
    public Food save(Food food) {
        return repo.save(food);
    }

    @Override
    public Iterable<Food> findByTag(String filter) {
        return repo.findByTag(filter);
    }

    @Override
    public int caloriesCount(User user){
        int result = 0;
        for (Food food: repo.findAll()
             ) {
            if (food.getAuthor().getUsername().equals(user.getUsername())){
                result += food.getCalories();
            }
        }
        return result;

    }
}

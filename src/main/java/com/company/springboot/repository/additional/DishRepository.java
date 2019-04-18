package com.company.springboot.repository.additional;

import com.company.springboot.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {

    List<Dish> findAllByCaloriesBetween (Integer from, Integer to);

}

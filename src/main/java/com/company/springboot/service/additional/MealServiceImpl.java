package com.company.springboot.service.additional;

import com.company.springboot.entity.Dish;
import com.company.springboot.entity.Meal;
import com.company.springboot.entity.Person;
import com.company.springboot.repository.additional.DishRepository;
import com.company.springboot.repository.additional.MealRepository;
import com.company.springboot.repository.additional.PersonRepository;
import com.company.springboot.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MealServiceImpl implements MealService {

    private final MealRepository repository;
    private final PersonRepository personRepository;
    private final DishRepository dishRepository;

    @Autowired
    public MealServiceImpl(MealRepository repository, PersonRepository personRepository, DishRepository dishRepository) {
        this.repository = repository;
        this.personRepository = personRepository;
        this.dishRepository = dishRepository;
    }

    @Override
    public List<Meal> getAll() {
        return repository.findAll();
    }

    @Override
    public Meal save(String description, Integer calories, Long userId) {
        Person person = personRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        Meal meal = new Meal(LocalDate.now(), description, calories, person);
        return repository.save(meal);
    }
    @Override
    public Meal getMeal(Long id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
    @Override
    public Meal updateMeal(Long id, String description,
                    Integer calories, Long userId) {
        repository.deleteById(id);
        Person person = personRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        Meal meal = new Meal(LocalDate.now(), description, calories, person);
        return repository.save(meal);
    }
    @Override
    public void deleteMeal(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Meal> findAllByDate(LocalDate localDate, Long userId) {
        return personRepository.findById(userId).orElseThrow(IllegalArgumentException::new)
                .getMeals().stream()
                .filter(meal -> meal.getDate().toString().equals(localDate.toString()))
                .collect(Collectors.toList());
    }

    @Override
    public String getMessageWithExcess(String date, Long userId) {
        LocalDate localDate = Util.parseLocalDate(date);
        Person person = personRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        int caloriesExpected = Util.calculateCaloriesNorma(person);

        int caloriesActual = findAllByDate(localDate, userId).stream()
                .mapToInt(Meal::getCalories)
                .sum();

        int result = caloriesActual - caloriesExpected;

        return  result > 0 ?
                "This day you get "+result+" calories more than needed" :
                "it`s ok, you can even get " + Math.abs(result) + " calories more";

    }

    @Override
    public List<Dish> findAllDishes(){
        return dishRepository.findAll();
    }

    @Override
    public Meal createMealFromDishes(String dishes, Long userId){
        List<Dish> dishList = Stream.of(dishes.split("\\s"))
                .filter(s -> s.matches("[0-9]++"))
                .mapToLong(Long::valueOf)
                .mapToObj(this::findDishById)
                .collect(Collectors.toList());

        Person person = personRepository.findById(userId).orElseThrow(IllegalArgumentException::new);

        StringBuilder description = new StringBuilder();
        int calories = 0;
        for (Dish dish: dishList
             ) {
            description.append(dish.getName()).append(" ");
            calories += dish.getCalories();
        }
        Meal meal = new Meal(LocalDate.now(), description.toString().trim(), calories, person);
        return repository.save(meal);
    }

    @Override
    public Dish findDishById(Long id) {
        return dishRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Dish saveDish(String name, Integer calories) {
        Dish dish = new Dish(name, calories);
        return dishRepository.save(dish);
    }


}

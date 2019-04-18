package com.company.springboot.controller.additional;

import com.company.springboot.entity.Meal;
import com.company.springboot.service.additional.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
public class MealController {

    private final MealService service;

    @Autowired
    public MealController(MealService service) {
        this.service = service;
    }

    @GetMapping("/mealsform")
    public String persons(Map<String, Object> model) {
        List<Meal> meals = service.getAll();

        model.put("meals", meals);

        return "mealsform";
    }

    @PostMapping("/mealsform")
    public String add(@RequestParam String description, @RequestParam Integer calories,
                      @RequestParam Long userId, Map<String, Object> model) {

        service.save(description, calories, userId);

        List<Meal> meals = service.getAll();

        model.put("meals", meals);

        return "mealsform";
    }

    @PostMapping("/mealsfilter")
    public String filter(@RequestParam LocalDateTime mealsfilter, Map<String, Object> model) {
        List<Meal> meals;

        if (mealsfilter != null ) {
            meals = service.findAllByDate(mealsfilter.toLocalDate(), 1001L);//todo
        } else {
            meals = service.getAll();
        }

        model.put("meals", meals);

        return "personsform";
    }


}

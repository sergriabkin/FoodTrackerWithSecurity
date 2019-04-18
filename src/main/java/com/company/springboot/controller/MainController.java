package com.company.springboot.controller;

import com.company.springboot.domain.Food;
import com.company.springboot.domain.User;
import com.company.springboot.repository.FoodRepo;
import com.company.springboot.service.FoodService;
import com.company.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    private final FoodService foodService;

    @Autowired
    public MainController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Food> messages = foodService.findAll();

        model.put("messages", messages);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam Integer calories,
            @RequestParam(required = false) String papered,
            @RequestParam String tag, Map<String, Object> model
    ) {

        Food food;

        if (papered == null || papered.equals("none") ){
            food = new Food(calories, tag, user);
        }

        else if (papered.equals("dish1")) {
            food = new Food(100, "Гречка", user);
        }
        else if (papered.equals("dish2")) {
            food = new Food(200, "Рис", user);
        }
        else if (papered.equals("dish3")) {
            food = new Food(300, "Картошка", user);
        }

        else food = null;

        foodService.save(food);

        Iterable<Food> messages = foodService.findAll();
        model.put("messages", messages);

        int caloriesCount = foodService.caloriesCount(user);
        model.put("caloriesCount", "Вы потребили "+caloriesCount+" калорий");

        int caloriesNorm = 2000;
        int result = caloriesCount - caloriesNorm;
        String review = result > 0 ?
                "Внимание! Вы потребили на "+result+" калорий больше нормы!" :
                "можно потребить еще " + Math.abs(result) + " калорий";
        model.put("review", review);

        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Food> messages;

        if (filter != null && !filter.isEmpty()) {
            messages = foodService.findByTag(filter);
        } else {
            messages = foodService.findAll();
        }

        model.put("messages", messages);

        return "main";
    }
}
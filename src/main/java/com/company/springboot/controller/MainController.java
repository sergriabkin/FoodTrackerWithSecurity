package com.company.springboot.controller;

import com.company.springboot.domain.Food;
import com.company.springboot.domain.User;
import com.company.springboot.repository.FoodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private FoodRepo foodRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Food> messages = foodRepo.findAll();

        model.put("messages", messages);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam Integer calories,
            @RequestParam String tag, Map<String, Object> model
    ) {
        Food food = new Food(calories, tag, user);

        foodRepo.save(food);

        Iterable<Food> messages = foodRepo.findAll();

        model.put("messages", messages);

        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Food> messages;

        if (filter != null && !filter.isEmpty()) {
            messages = foodRepo.findByTag(filter);
        } else {
            messages = foodRepo.findAll();
        }

        model.put("messages", messages);

        return "main";
    }
}
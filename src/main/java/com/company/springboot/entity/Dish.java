package com.company.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DISHES")
public class Dish {

    public Dish(String name, Integer calories) {
        this.name = name;
        this.calories = calories;
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private Integer calories;

    public String getName() {
        return name;
    }

    public Integer getCalories() {
        return calories;
    }
}

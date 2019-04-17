package com.company.springboot.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@ToString
@Data
@NoArgsConstructor
@Table(name = "MEALS")
public class Meal {

//    public Meal(String description, Integer calories, Person person) {
//        this(LocalDate.now(), description, calories, person);
//    }
    public Meal(LocalDate date, String description, Integer calories, Person person) {
        this.date = date;
        this.description = description;
        this.calories = calories;
        this.person = person;
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "DATE")
    private LocalDate date;

    @Column
    private String description;

    @Column
    private Integer calories;

    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    private Person person;

    public LocalDate getDate() {
        return date;
    }

    public Integer getCalories() {
        return calories;
    }

}

package com.company.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PHONES")
public class Phone {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String number;

    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    private Person person;

}

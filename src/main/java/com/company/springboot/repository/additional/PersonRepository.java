package com.company.springboot.repository.additional;

import com.company.springboot.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByNameIgnoreCase(String name);

    List<Person> findByNameAndAge(String name, Integer age);

    @Query(value = "SELECT * FROM Person WHERE age = :age ORDER BY name", nativeQuery = true)
    List<Person> findByAgeOrderByName(@Param("age") Integer age);

    List<Person> findByNameLike(String name);

    List<Person> findByAgeBetween(Integer from, Integer to);
}

package com.company.springboot.repository.additional;

import com.company.springboot.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
    List<Phone> findAllByPersonNameOrderByNumber(String name);
}

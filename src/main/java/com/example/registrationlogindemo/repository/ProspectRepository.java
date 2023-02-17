package com.example.registrationlogindemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.registrationlogindemo.entity.Prospect;


@Component 
public interface ProspectRepository extends JpaRepository<Prospect, Long> {

}


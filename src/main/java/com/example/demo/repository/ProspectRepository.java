package com.example.demo.repository;

import com.example.demo.entity.Prospect;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProspectRepository extends JpaRepository<Prospect, Integer> {
}

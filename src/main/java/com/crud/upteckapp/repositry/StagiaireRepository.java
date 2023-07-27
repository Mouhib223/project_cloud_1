package com.crud.upteckapp.repositry;

import com.crud.upteckapp.model.Stagiaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StagiaireRepository extends JpaRepository<Stagiaire, Long> {
}
package com.crud.upteckapp.Services;

import com.crud.upteckapp.model.Stagiaire;
import com.crud.upteckapp.repositry.StagiaireRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StagiaireService {
    private final StagiaireRepository repo;

    public StagiaireService(StagiaireRepository repo) {
        this.repo = repo;
    }

    public List<Stagiaire> listAll() {
        return repo.findAll();
    }

    public void save(Stagiaire stagiaire) {
        repo.save(stagiaire);
    }

    public Stagiaire get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}

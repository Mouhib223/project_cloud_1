package com.crud.upteckapp.controller;
import com.crud.upteckapp.Services.StagiaireService;
import com.crud.upteckapp.model.Stagiaire;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")

@RestController
@RequestMapping("/Stagiaires")//hedhi combinaison mabin @Controller et @ResponseBody
public class StagiaireController {
    private final StagiaireService service;

    public StagiaireController(StagiaireService service) {
        this.service = service;
    }
    @GetMapping("/")
    public List<Stagiaire> getStagiaires(){

        return service.listAll();
    }
    @GetMapping("/{id}")
    public Stagiaire getStagiaire(@PathVariable Long id)
    {
        return service.get(id);

    }
    @PostMapping("/add")
    public void addStagiaire(@RequestBody Stagiaire stagiaire) {
        service.save(stagiaire);
    }
    @PutMapping("/{id}")
    public void update(@RequestBody Stagiaire stagiaire, @PathVariable Long id) {
        // on verifie d'abord si le stagiaire existe
        Stagiaire existingStagiaire = service.get(id);
        // puis on mis à jour
        service.save(stagiaire);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

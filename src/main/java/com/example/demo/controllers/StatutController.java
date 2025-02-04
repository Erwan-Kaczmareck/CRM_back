package com.example.demo.controllers;

import com.example.demo.entity.Statut;
import com.example.demo.Service.StatutService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/statuts")
public class StatutController {

    private final StatutService statutService;

    public StatutController(StatutService statutService) {
        this.statutService = statutService;
    }

    @GetMapping
    public List<Statut> getAllStatuts() {
        return statutService.getAllStatuts();
    }

    @PostMapping
    public Statut createStatut(@RequestBody Statut statut) {
        return statutService.saveStatut(statut);
    }

    @PostMapping("/init")
    public String initStatuts() {
        statutService.initStatuts();
        return "Statuts insérés avec succès !";
    }

    @GetMapping("/{id}")
    public Statut getStatutById(@PathVariable Integer id) {
        return statutService.getStatutById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteStatut(@PathVariable Integer id) {
        statutService.deleteStatut(id);
    }
}
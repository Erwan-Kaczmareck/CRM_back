package com.example.demo.Service;

import com.example.demo.entity.Statut;
import com.example.demo.repository.StatutRepository;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.util.List;

@Service
public class StatutService {
    private final StatutRepository statutRepository;

    public StatutService(StatutRepository statutRepository) {
        this.statutRepository = statutRepository;
    }

    public List<Statut> getAllStatuts() {
        return statutRepository.findAll();
    }

    public Statut saveStatut(Statut statut) {
        return statutRepository.save(statut);
    }

    public Statut getStatutById(Integer id) {
        return statutRepository.findById(id).orElse(null);
    }

    public void deleteStatut(Integer id) {
        statutRepository.deleteById(id);
    }

    @PostConstruct
    public void initStatuts() {

        System.out.println("Exécution de initStatuts()...");
        if (statutRepository.count() == 0) {
            List<Statut> defaultStatuts = List.of(
                    new Statut("Prospect"),
                    new Statut("Perdue"),
                    new Statut("Candidat"),
                    new Statut("Etudiant")
            );
            statutRepository.saveAll(defaultStatuts);
            System.out.println("Statuts par défaut insérés !");
        } else {
            System.out.println("Les statuts existent déjà.");
        }
    }
}

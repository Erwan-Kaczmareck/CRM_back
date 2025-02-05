package com.example.demo.controllers;

import com.example.demo.entity.Prospect;
import com.example.demo.Service.ProspectService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.List;
@RestController
@RequestMapping("/api/prospects")
@CrossOrigin(origins = "*")
public class ProspectController {
    private final ProspectService prospectService;

    public ProspectController(ProspectService prospectService) {
        this.prospectService = prospectService;
    }

    @GetMapping
    public ResponseEntity<List<Prospect>> getAllProspects() {
        try {
            List<Prospect> prospects = prospectService.getAllProspects();
            return ResponseEntity.ok(prospects);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<?> createProspect(@Valid @RequestBody Prospect prospect) {
        try {
            // Vérification des champs obligatoires : Nom et Prénom
            if (prospect.getNom() == null || prospect.getNom().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nom est obligatoire.");
            }
            if (prospect.getPrenom() == null || prospect.getPrenom().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le prénom est obligatoire.");
            }

            // Vérification de l'année de recrutement (entre 1900 et 3000)
            if (prospect.getAnneeRecrutement() != null) {
                Short anneeRecrutement = prospect.getAnneeRecrutement();
                if (anneeRecrutement < 1900 || anneeRecrutement > 3000) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'année de recrutement doit être entre 1900 et 3000.");
                }
            }

            // Vérification de l'année (entre 1900 et 3000)
            if (prospect.getAnnee() != null) {
                Short annee = prospect.getAnnee();
                if (annee < 1900 || annee > 3000) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'année doit être entre 1900 et 3000.");
                }
            }

            // Vérification du téléphone (doit contenir exactement 10 chiffres)
            if (prospect.getTelephone() != null && !prospect.getTelephone().matches("^[0-9]{10}$")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le numéro de téléphone doit contenir 10 chiffres.");
            }

            // Vérification de l'email (format valide)
            if (prospect.getMail() != null && !prospect.getMail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'email fourni est invalide.");
            }

            // Vérification du statut (l'ID du statut doit être présent)
            if (prospect.getStatut() != null && prospect.getStatut().getIdStatut() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le statut doit être valide.");
            }

            // Vérification des champs texte (ils doivent être des chaînes non nulles)
            if (prospect.getCodePostale() != null && prospect.getCodePostale().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le code postal doit être une chaîne de caractères valide.");
            }
            if (prospect.getVille() != null && prospect.getVille().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La ville doit être une chaîne de caractères valide.");
            }

            if (prospect.getEtablissement() != null && prospect.getEtablissement().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'établissement doit être une chaîne de caractères valide.");
            }

            if (prospect.getNiveauActuel() != null && prospect.getNiveauActuel().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le niveau actuel doit être une chaîne de caractères valide.");
            }
            if (prospect.getDiplomePrepare() != null && prospect.getDiplomePrepare().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le diplôme préparé doit être une chaîne de caractères valide.");
            }
            if (prospect.getSpecialite() != null && prospect.getSpecialite().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La spécialité doit être une chaîne de caractères valide.");
            }
            if (prospect.getEntreProchaineAnnee() != null && prospect.getEntreProchaineAnnee().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le champ 'Entre prochaine année' doit être une chaîne de caractères valide.");
            }
            if (prospect.getCommentaire() != null && prospect.getCommentaire().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le commentaire doit être une chaîne de caractères valide.");
            }

            // Sauvegarde du prospect
            Prospect savedProspect = prospectService.saveProspect(prospect);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProspect);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur lors de la création du prospect.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProspectById(@PathVariable Integer id) {
        Prospect prospect = prospectService.getProspectById(id);
        if (prospect == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prospect non trouvé avec l'id " + id);
        }
        return ResponseEntity.ok(prospect);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProspect(@PathVariable Integer id) {
        try {
            // Vérifier si le prospect existe avant de supprimer
            Prospect existingProspect = prospectService.getProspectById(id);
            if (existingProspect == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prospect non trouvé avec l'id " + id);
            }
            prospectService.deleteProspect(id);
            return ResponseEntity.ok("Prospect supprimé avec succès.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la suppression du prospect.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProspect(@PathVariable Integer id, @Valid @RequestBody Prospect updatedProspect) {

        // Vérification de la présence de 'Nom' et 'Prénom' (doivent être obligatoires)
        if (updatedProspect.getNom() == null || updatedProspect.getNom().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nom est obligatoire.");
        }
        if (updatedProspect.getPrenom() == null || updatedProspect.getPrenom().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le prénom est obligatoire.");
        }

        // Vérification de l'année de recrutement (si remplie, elle doit être un nombre entre 1900 et 3000)
        if (updatedProspect.getAnneeRecrutement() != null) {
            // Vérification si l'année est un nombre
            if (!(updatedProspect.getAnneeRecrutement() instanceof Number)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'année de recrutement doit être un nombre.");
            }

            Short anneeRecrutement = updatedProspect.getAnneeRecrutement();

            // Vérification que l'année de recrutement est entre 1900 et 3000
            if (anneeRecrutement < 1900 || anneeRecrutement > 3000) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'année de recrutement doit être entre 1900 et 3000.");
            }
        }

        // Vérification de l'année (si remplie, elle doit être un nombre entre 1900 et 3000)
        if (updatedProspect.getAnnee() != null) {
            // Vérification si l'année est un nombre
            if (!(updatedProspect.getAnnee() instanceof Number)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'année doit être un nombre.");
            }

            Short annee = updatedProspect.getAnnee();

            // Vérification que l'année est entre 1900 et 3000
            if (annee < 1900 || annee > 3000) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'année doit être entre 1900 et 3000.");
            }
        }

        // Vérification du type de 'Nom' (doit être une chaîne de caractères)
        if (updatedProspect.getNom() != null && !updatedProspect.getNom().isEmpty() && updatedProspect.getNom() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nom doit être une chaîne de caractères.");
        }

        // Vérification du type de 'Prénom' (doit être une chaîne de caractères)
        if (updatedProspect.getPrenom() != null && !updatedProspect.getPrenom().isEmpty() && updatedProspect.getPrenom() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le prénom doit être une chaîne de caractères.");
        }

        // Vérification du format du 'Numéro de téléphone' (doit être un nombre avec 10 chiffres)
        if (updatedProspect.getTelephone() != null && !updatedProspect.getTelephone().matches("^[0-9]{10}$")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le numéro de téléphone doit être valide avec 10 chiffres.");
        }

        // Vérification du format de 'Email' (doit être une adresse email valide)
        if (updatedProspect.getMail() != null && !updatedProspect.getMail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'email fourni est invalide.");
        }

        // Vérification du type de 'Code Postal' (doit être une chaîne de caractères)
        if (updatedProspect.getCodePostale() != null && !updatedProspect.getCodePostale().isEmpty() && updatedProspect.getCodePostale() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le code postal doit être une chaîne de caractères.");
        }

        // Vérification du type de 'Ville' (doit être une chaîne de caractères)
        if (updatedProspect.getVille() != null && !updatedProspect.getVille().isEmpty() && updatedProspect.getVille() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La ville doit être une chaîne de caractères.");
        }

        // Vérification du type de 'Niveau actuel' (doit être une chaîne de caractères)
        if (updatedProspect.getNiveauActuel() != null && !updatedProspect.getNiveauActuel().isEmpty() && updatedProspect.getNiveauActuel() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le niveau actuel doit être une chaîne de caractères.");
        }

        // Vérification du type de 'Diplôme préparé' (doit être une chaîne de caractères)
        if (updatedProspect.getDiplomePrepare() != null && !updatedProspect.getDiplomePrepare().isEmpty() && updatedProspect.getDiplomePrepare() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le diplôme préparé doit être une chaîne de caractères.");
        }

        // Vérification du type de 'Spécialité' (doit être une chaîne de caractères)
        if (updatedProspect.getSpecialite() != null && !updatedProspect.getSpecialite().isEmpty() && updatedProspect.getSpecialite() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La spécialité doit être une chaîne de caractères.");
        }
        if (updatedProspect.getEtablissement() != null && updatedProspect.getEtablissement().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'établissement doit être une chaîne de caractères valide.");
        }

        // Vérification du type de 'Entre prochaine année' (doit être une chaîne de caractères)
        if (updatedProspect.getEntreProchaineAnnee() != null && !updatedProspect.getEntreProchaineAnnee().isEmpty() && updatedProspect.getEntreProchaineAnnee() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le champ 'Entre prochaine année' doit être une chaîne de caractères.");
        }

        // Vérification du type de 'Commentaire' (doit être une chaîne de caractères)
        if (updatedProspect.getCommentaire() != null && !updatedProspect.getCommentaire().isEmpty() && updatedProspect.getCommentaire() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le commentaire doit être une chaîne de caractères.");
        }

        // Vérification du 'Statut' (doit être valide, l'ID ne peut pas être null)
        if (updatedProspect.getStatut() != null && updatedProspect.getStatut().getIdStatut() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le statut doit être valide.");
        }



        // Appel au service pour mettre à jour le prospect
        Prospect updated = prospectService.updateProspect(id, updatedProspect);
        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prospect non trouvé avec l'id " + id);
        }

        return ResponseEntity.ok(updated);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}

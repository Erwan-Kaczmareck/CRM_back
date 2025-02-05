package com.example.demo.Service;

import com.example.demo.entity.Prospect;
import com.example.demo.repository.ProspectRepository;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.Optional;

@Service
public class ProspectService {
    private final ProspectRepository prospectRepository;

    public ProspectService(ProspectRepository prospectRepository) {
        this.prospectRepository = prospectRepository;
    }

    public List<Prospect> getAllProspects() {
        return prospectRepository.findAll();
    }

    public Prospect saveProspect(Prospect prospect) {
        return prospectRepository.save(prospect);
    }

    public Prospect getProspectById(Integer id) {
        Optional<Prospect> prospect = prospectRepository.findById(id);
        if (prospect.isPresent()) {
            return prospect.get();
        } else {
            return null;  // Retourne null si le prospect n'est pas trouvé
        }
    }

    public void deleteProspect(Integer id) {
        if (prospectRepository.existsById(id)) {
            prospectRepository.deleteById(id);
        }
    }

    public Prospect updateProspect(Integer id, Prospect prospectDetails) {
        Optional<Prospect> existingProspect = prospectRepository.findById(id);
        if (existingProspect.isPresent()) {
            Prospect prospect = existingProspect.get();
            // Mise à jour des informations du prospect
            prospect.setType(prospectDetails.getType());
            prospect.setOrigineLead(prospectDetails.getOrigineLead());
            prospect.setMois(prospectDetails.getMois());
            prospect.setAnnee(prospectDetails.getAnnee());
            prospect.setCodePostale(prospectDetails.getCodePostale());
            prospect.setNom(prospectDetails.getNom());
            prospect.setPrenom(prospectDetails.getPrenom());
            prospect.setTelephone(prospectDetails.getTelephone());
            prospect.setMail(prospectDetails.getMail());
            prospect.setNiveauActuel(prospectDetails.getNiveauActuel());
            prospect.setDiplomePrepare(prospectDetails.getDiplomePrepare());
            prospect.setSpecialite(prospectDetails.getSpecialite());
            prospect.setVille(prospectDetails.getVille());
            prospect.setAnneeRecrutement(prospectDetails.getAnneeRecrutement());
            prospect.setEntreProchaineAnnee(prospectDetails.getEntreProchaineAnnee());
            prospect.setCommentaire(prospectDetails.getCommentaire());
            prospect.setStatut(prospectDetails.getStatut());

            return prospectRepository.save(prospect);
        } else {
            return null;  // Retourne null si le prospect n'existe pas
        }
    }
}

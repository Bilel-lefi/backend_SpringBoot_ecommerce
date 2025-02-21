package ecommerce.rj.Commande;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    // Récupérer toutes les commandes
    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    // Récupérer une commande par son ID
    public Commande getCommandeById(Long id) {
        return commandeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commande non trouvée avec l'ID : " + id));
    }

    // Créer une nouvelle commande
    public Commande createCommande(Commande commande) {
        // Optionnel: valider les champs de la commande (nom, adresse, etc.)
        if (commande.getNom().isEmpty() || commande.getAdresse().isEmpty()) {
            throw new IllegalArgumentException("Nom et adresse doivent être remplis");
        }
        return commandeRepository.save(commande);
    }

    // Mettre à jour une commande existante
    public Commande updateCommande(Long id, Commande commandeDetails) {
        Commande commande = getCommandeById(id); // Vérifier si la commande existe
        commande.setNom(commandeDetails.getNom());
        commande.setPrenom(commandeDetails.getPrenom());
        commande.setTelephone(commandeDetails.getTelephone());
        commande.setAdresse(commandeDetails.getAdresse());
        return commandeRepository.save(commande);
    }

    // Supprimer une commande
    public void deleteCommande(Long id) {
        Commande commande = getCommandeById(id); // Vérifier si la commande existe
        commandeRepository.delete(commande);
    }
}

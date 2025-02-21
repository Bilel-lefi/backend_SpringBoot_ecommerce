package ecommerce.rj.Commande;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commandes")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;

    // Récupérer toutes les commandes
    @GetMapping
    public List<Commande> getAllCommandes() {
        return commandeService.getAllCommandes();
    }

    // Créer une nouvelle commande
    @PostMapping
    public ResponseEntity<Commande> createCommande(@RequestBody Commande commande) {
        Commande createdCommande = commandeService.createCommande(commande);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCommande);
    }
}

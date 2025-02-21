package ecommerce.rj.Article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/articles")
@CrossOrigin(origins = "http://localhost:5174") // Permet les requêtes depuis React
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    // Récupérer tous les articles
    @GetMapping
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    // Récupérer un article par ID
    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
        Article article = articleService.getArticleById(id);
        return article != null ? ResponseEntity.ok(article) : ResponseEntity.notFound().build();
    }

    // Ajouter un article avec image en utilisant multipart/form-data
    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestParam("name") String name,
                                                 @RequestParam("description") String description,
                                                 @RequestParam("price") Double price,
                                                 @RequestParam("category") String category,
                                                 @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {

        Article newArticle = new Article();
        newArticle.setName(name);
        newArticle.setDescription(description);
        newArticle.setPrice(price);
        newArticle.setCategory(category);

        if (image != null && !image.isEmpty()) {
            String imageUrl = articleService.saveImage(image); // Sauvegarde de l'image et récupération de l'URL
            newArticle.setImage(imageUrl);
        }

        Article createdArticle = articleService.createArticle(newArticle);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdArticle);
    }

    // Mettre à jour un article
    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id,
                                                 @RequestParam("name") String name,
                                                 @RequestParam("description") String description,
                                                 @RequestParam("price") Double price,
                                                 @RequestParam("category") String category,
                                                 @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {

        Article updatedArticle = articleService.updateArticle(id, name, description, price, category, image);
        return updatedArticle != null ? ResponseEntity.ok(updatedArticle) : ResponseEntity.notFound().build();
    }

    // Supprimer un article
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }
}

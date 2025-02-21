package ecommerce.rj.Article;

import ecommerce.rj.Article.Article;
import ecommerce.rj.Article.ArticleRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Article getArticleById(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    public Article updateArticle(Long id, Article articleDetails) {
        return articleRepository.findById(id).map(article -> {
            article.setName(articleDetails.getName());
            article.setDescription(articleDetails.getDescription());
            article.setPrice(articleDetails.getPrice());
            article.setCategory(articleDetails.getCategory());
            article.setImage(articleDetails.getImage());
            return articleRepository.save(article);
        }).orElse(null);
    }

    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }
}

package ecommerce.rj.Article;

public class ArticleRequest {
    private String name;
    private Double price;
    private String description;
    private String category;
    private String image; // Base64 string

    // Getters et Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public Article toArticle() {
        Article article = new Article();
        article.setName(this.name);
        article.setPrice(this.price);
        article.setDescription(this.description);
        article.setCategory(this.category);
        article.setImage(this.image);
        return article;
    }
}

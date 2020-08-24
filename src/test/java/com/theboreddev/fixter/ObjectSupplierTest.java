package com.theboreddev.fixter;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ObjectSupplierTest {

    @Test
    public void shouldBuildAnInstanceOfAGivenType() {

        Article article = ObjectSupplier.supplyObject(Article.class);

        assertThat(article).matches(this::isAValidArticle);
    }


    private boolean isAValidArticle(Article article) {
        return article != null &&
                article.getId() > 0 &&
                article.getTitle().length() > 0 &&
                article.getDescription().length() > 0 &&
                article.getPrice() > 0.0;
    }

    static class Article {
        private final int id;
        private final String title;
        private final String description;
        private final double price;

        public Article(int id, String title, String description, double price) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.price = price;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public double getPrice() {
            return price;
        }
    }
}

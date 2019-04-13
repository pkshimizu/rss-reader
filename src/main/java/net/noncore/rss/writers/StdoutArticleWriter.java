package net.noncore.rss.writers;

import net.noncore.rss.Article;

import java.io.IOException;
import java.util.List;

public class StdoutArticleWriter implements ArticleWriter {
    @Override
    public void write(List<Article> articles) throws IOException {
        articles.forEach(article -> {
            System.out.println(String.format(
                    "title: %s\n" +
                    "body: %s\n",
                    article.getTitle(), article.getBody()));
        });
    }
}

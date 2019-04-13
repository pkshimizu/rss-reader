package net.noncore.rss.writers;

import net.noncore.rss.Article;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileArticleWriter implements ArticleWriter {
    private File file;

    public FileArticleWriter(File file) {
        this.file = file;
    }

    @Override
    public void write(List<Article> articles) throws IOException {
        try (FileWriter writer = new FileWriter(file)) {
            for (Article article : articles) {
                writer.write(String.format(
                        "title: %s\n" +
                        "body: %s\n\n",
                        article.getTitle(), article.getBody()));
            }
        }
    }
}

package net.noncore.rss.writers;

import net.noncore.rss.Article;

import java.io.IOException;
import java.util.List;

public interface ArticleWriter {
    void write(List<Article> articles) throws IOException;
}

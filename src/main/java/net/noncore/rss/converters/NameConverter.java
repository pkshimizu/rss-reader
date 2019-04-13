package net.noncore.rss.converters;

import net.noncore.rss.Article;

public class NameConverter implements ArticleConverter {
    @Override
    public Article convert(Article article) {
        return new Article(
                article.getTitle(),
                article.getBody().replace("ユーザベース", "UZABASE"));
    }
}

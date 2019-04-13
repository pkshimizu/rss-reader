package net.noncore.rss.converters;

import net.noncore.rss.Article;

import java.util.Collections;
import java.util.List;

public class CompositeRssConverter implements ArticleConverter {
    List<ArticleConverter> converters;

    public CompositeRssConverter(List<ArticleConverter> converters) {
        this.converters = Collections.unmodifiableList(converters);
    }

    @Override
    public Article convert(Article article) {
        for (ArticleConverter converter : converters) {
            article = converter.convert(article);
        }
        return article;
    }
}

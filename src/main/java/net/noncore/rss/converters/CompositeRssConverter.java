package net.noncore.rss.converters;

import net.noncore.rss.Article;

import java.util.Collections;
import java.util.List;

public class CompositeRssConverter implements RssConverter {
    List<RssConverter> converters;

    public CompositeRssConverter(List<RssConverter> converters) {
        this.converters = Collections.unmodifiableList(converters);
    }

    @Override
    public Article convert(Article article) {
        for (RssConverter converter : converters) {
            article = converter.convert(article);
        }
        return article;
    }
}

package net.noncore.rss.converters;

import net.noncore.rss.Article;

public interface RssConverter {
    Article convert(Article article);
}
